# Word2VecModelTextWrangling
This code tests the use of a Word2Vec model stored in MapDB file-type
hash table.

It is time-consuming to train a Word2Vec model using `deeplearning4j`
for use in Java,
while it can be easily done using the original C code or the wrapped
Python package, `[gensim](https://radimrehurek.com/gensim/)`.
Such model can be saved in text format. Here is an example converting
google pre-trained vectors into text format.

```python
from gensim.models import Word2Vec
model = Word2Vec.load_word2vec_format('GoogleNews-vectors-negative300.bin.gz', binary=True)
model.save_word2vec_format('GoogleNews-vectors-negative300.txt', binary=False)
```

The code `home.textmining.word2vec.VectorsTexttoMapDB` is the code
with main block that converts the text file into MapDB, with the first argument
being the text file path, and the second the MapDB path. This class
also provides a factory method that returns a `Word2Vec` model given
a MapDB path:

```Java
public static Word2VecModel getMapDBWord2VecModel(File hashMapFile);
```