from sklearn.datasets import fetch_20newsgroups
from sklearn import linear_model, svm
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.feature_extraction.text import TfidfTransformer
from sklearn.naive_bayes import MultinomialNB
from sklearn.pipeline import Pipeline
import numpy as np
import nltk
import os
import csv
import warnings
warnings.filterwarnings("ignore")

### Import data ###
train_collection = fetch_20newsgroups(subset='train', shuffle=True)
sample_collection = fetch_20newsgroups(subset='test', shuffle=True)

# Declare amount of articles
amount_articles = 100

# clear news directory
def sanitize_directory(directory_name):
     collection_file = [ f for f in os.listdir(directory_name) if f.endswith(".txt") ]

     for file in collection_file:
        os.remove(os.path.join(directory_name, file))

# Collect 50 news articles and save them in a folder "Data"
def collect_news(amount_articles):
    sanitize_directory("data")

    index = 0

    while index != amount_articles :
        file_name = "data/"+str(index)+"_news_"+str(sample_collection.target_names[sample_collection.target[index]]).split('.')[-1] + ".txt"
        fd = open(file_name,'w')
        fd.write(sample_collection.data[index])
        fd.close()
        index += 1

# retrieve a model to classify news document to predict main topic
def get_model():

    model = Pipeline([('vect', CountVectorizer()),('tfidf', TfidfTransformer()),('clf', linear_model.SGDClassifier())])
    model.fit(train_collection.data, train_collection.target)

    return model

#  Generate csv file containing the document_id, class_predicted
def csv_generator():
    fd_csv = open('model/predicted_class.csv','w',newline='')

    writer = csv.writer(fd_csv)

    model = get_model()

    for file in os.listdir('data'):
        row = []
        if file.endswith(".txt"):
            fd = open('Data/' + file, 'r')
            row.append(str(file).split('_')[0])
            row.append(sample_collection.target_names[model.predict([fd.read()])[0]].split('.')[-1])
            writer.writerow(row)
            fd.close()

    fd_csv.close()

# Format rdf with document id and predicted subject
def formatter(document_id, subject_predicted):

        str_rdf = '<rdf:Description rdf:about="document_' + str(document_id) + '">\n'

        str_rdf += '\t<ex:is_about>'+subject_predicted+"</ex:is_about>\n"

        str_rdf += '</rdf:Description>\n'

        return str_rdf

# Generate xml file containing the rdf model for every document_id
def xml_generator():

    dictionary = dict()

    fd_rdf = open('model/rdf_generated.xml','w')
    fd_rdf.write('<?xml version="1.0"?>\n')
    fd_rdf.write('<rdf:RDF xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#" xmlns:ex="http://purl.org/dc/elements/1.1/">\n')

    with open('model/predicted_class.csv', newline = '') as fd_csv:
        reader = csv.reader(fd_csv, delimiter = ',')
        for row in reader:
            dictionary[int(row[0])] = row[1]

    for key, value in dictionary.items():
        fd_rdf.write(formatter(key, value))
    fd_rdf.write('</rdf:RDF>')
    fd_rdf.close()

print("Start of script")
print("------------ collect news data : start -------------")
collect_news(amount_articles)
print("------------ collect news data : end -------------")

print("------------ retrieve trained model data : start -------------")
get_model()
print("------------ retrieve trained model data : end -------------")

print("------------ Generate csv file : start -------------")
csv_generator()
print("------------ Generate csv file : end -------------")

print("------------ Generate rdf file : start -------------")
xml_generator()
print("------------ Generate rdf file : end -------------")
print("End of script")


model = Pipeline([('vect', CountVectorizer()), ('tfidf', TfidfTransformer()), ('clf', MultinomialNB())])
model = model.fit(train_collection.data, train_collection.target)

print("Bayesian score :", np.mean(sample_collection.target == model.predict(sample_collection.data)))

model = Pipeline([('vect',CountVectorizer()),('tfidf',TfidfTransformer()),('clf',linear_model.SGDClassifier())])
model.fit(train_collection.data, train_collection.target)
print("SVM score :", np.mean(model.predict(sample_collection.data) == sample_collection.target))
