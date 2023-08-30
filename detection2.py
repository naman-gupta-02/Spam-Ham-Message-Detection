import sys
import pandas as pd #pandas is used to read data from a file .. ie data modification
from sklearn.svm import SVC
from sklearn.model_selection import train_test_split
from sklearn.feature_extraction.text import CountVectorizer #string -> int for learning in svm (uses vector made from frequency of word)

data = pd.read_csv("Spam2.csv")
#print(data.info())

X = data["Message"].values
Y = data["Category"].values

X_train, X_test, Y_train, Y_test = train_test_split(X, Y, test_size = 0.2, random_state = 0)

cv = CountVectorizer()
X_train = cv.fit_transform(X_train)
X_test = cv.transform(X_test)
#fit is used to find mean and standard deviation of the data passed
#transform does scaling 

classifier = SVC(kernel = 'rbf', random_state = 10)
classifier.fit(X_train, Y_train)
#rbf is good for text classification

scoreval = classifier.score(X_test, Y_test)
print(scoreval)
#returns mean accuracy of the function

# Preprocess the new website using the same vectorizer
new_message = sys.argv[1]
new_message_features = cv.transform([new_message])

# Predict the label of the new website
prediction = classifier.predict(new_message_features)
print(prediction[0])