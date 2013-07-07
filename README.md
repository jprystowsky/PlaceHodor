PlaceHodor
==========

Not your grandpa's Lorem Ipsum generator.

About
-----

PlaceHodor is a fast, flexible framework for generating placeholder sentences algorithmically, implemented to generate
ones as spoken by Hodor. That is to say, "Hodor Hodor Hodor, Hodor Hodor. Hodor." Use PlaceHodor whenever you've got
space to fill and no content with which to fill it. Let PlaceHodor be your placeholder.

Architecture
------------

PlaceHodor is a RESTful service built on JAX-RS. To ease boilerplating and make things flexible, it uses
[Google Guice](code.google.com/p/google-guice/) for <abbr title="dependency injection">DI</abbr>. Data transfer
mapping is provided by [Google Gson](code.google.com/p/google-gson/). It uses
[Apache Commons Lang](https://commons.apache.org/proper/commons-lang/) in a couple places for string manipulation.

Design
------

The `Sentence` resource defines the primary API endpoint at `/sentence.` This class contains minimal code for responding
to requests and primarily calls upon its (injected, concretely-implemented) `SentenceFactory` to get its sentence(s).

The `SentenceFactory` uses its `LexiconFactory` and `SentenceTemplateFactory` (also Guice-injected) to create
sentences. Here, it is concretely implemented in `GibberishSentenceFactory,` which creates specific words from the
lexicon in the order specified by the template without regard to grammar or syntax.

The `SentenceTemplateFactory` creates `Iterable`s of a collection of tokens representing the structure of a sentence.
It's implemented in PlaceHodor by `RandomSentenceTemplateFactory,` which designs fairly meaningless sentence templates.
(If they have syntactic validity, it's only by accident.)

Finally, the `LexiconFactory` interface (via the Abstract Factory Pattern) defines a factory for obtaining `Set`s of
words organized by type (noun, verb, etc.), to create a "lexicon."

The Result
----------

```
GET /sentence HTTP/1.1

HTTP/1.1 200 OK
Content-Type: application/json
"Hodor Hodor Hodor;  Hodor Hodor Hodor --  Hodor Hodor ...  Hodor --  Hodor Hodor."
```