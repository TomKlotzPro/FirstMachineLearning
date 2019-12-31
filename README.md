## Abstract

In this project, the objective is to build a simple end to end pipeline in order to extract knowledge from a set of documents. To do that, I will from 100 news articles collected from sklearn datasets, generate a rdf graph and query the generated graph. Finaly, I will use reasoning to enrich and simplify the graph.

## Introduction

The project will be done in 2 parts. The first part, in python will be for collecting and transformating the data into a rdf graph. The second part, in Java part will be for querying the generated graph and enriching the graph with reasoning in order to simplify it.

To execute the project, open a terminal and run :

```shell
# Install Python 3
brew install python3

# Create a Python Virtual Environment inside your directory
python3 -m venv venv

# Activate the Python Virtual Environment
source venv/bin/activate

# At any time, use the following command to deactivate it
deactivate

# install all dependancies 
pip install sklearn

pip install nltk

# Run the script 
python project.py

# copy generated data to our Jena app dataset 
cp model/rdf_generated.xml Jena/src/main/resources/data/

```

Secondly, open an IDE (Eclipse/IntelliJ) :

- import Jena project
- download maven dependencies 
- build the project
- Run it

## Approach

Python (project.py) :

- Collecting 100 news articles (from sklearn datasets // fetch_20newsgroup) and saving them in the data directory as id_title_class.txt.

- Training a model to classify news document in order to predict the main topic.

- Putting into CSV (Generated/document_class.csv) the ID, prediction of the 100 news articles. The performance of the model prediction is
0.8518321826872013 

- Creating a RDF file (Generated/rdf_generated.xml) containing the rdf header, the rdf description of all news articles and the predicted class associated.


Java ( JENA )

- Query the rdf generated previously with sparql in order to know how many documents with the subject « autos » there are.


- Enrich the extracted knowledge in order to generalize/simplify subjects :

- atheism, christian, Religion | autos, motorcycles, Auto | graphics, x, misc, hardware, IT



## Experiment and pipeline

### Dataset(s) used

I used the sklearn datasets (fetch_20newsgroup) to create my training and test dataset.

### Machine learning used

The machine learning I used first was the Naive bayesian classifier. This classifier gave me a precision score of 77%.

To better my precision score, I used an other classifier (SVM) which gave me a better precision score of 85%. Thus I choosed to use the SVM classifier for my model. Sparql queries

To test my rdf graph, I used the following query to see if it return to me the right documents with the subject autos :

```
PREFIX ex:<http://purl.org/dc/elements/1.1/>

SELECT ?autos

WHERE { ?autos ex:is_about "autos"}`
```

After that, for enriching my model, I did those rules :

```
[rule1: (?x ex:is_about "atheism") -> (?x ex:is_about "religion")]

[rule2: (?x ex:is_about "christian") -> (?x ex:is_about "religion")]

[rule3: (?x ex:is_about "motorcycles") -> (?x ex:is_about "autos")]

[rule4: (?x ex:is_about "misc") -> (?x ex:is_about "it")]

[rule5: (?x ex:is_about "graphics") -> (?x ex:is_about "it")]

[rule6: (?x ex:is_about "hardware") -> (?x ex:is_about "it")]

[rule7: (?x ex:is_about "x") -> (?x ex:is_about "it")]
``` 

And to see if those rules was working well, I’ve made those following queries :
```
PREFIX ex:<http://purl.org/dc/elements/1.1/>

SELECT ?religion

WHERE { ?religion ex:is_about "religion"}

PREFIX ex:<http://purl.org/dc/elements/1.1/>

SELECT ?it

WHERE { ?it ex:is_about "it"}

Results

Documents with subject autos :

SELECT ?autos

WHERE { ?autos ex:is_about "autos"}
```
## Result : 

---------------------------

| autos

=================

| <document_28> |

| <document_27> |

| <document_0> |

---------------------------
 RDF Graph is OK