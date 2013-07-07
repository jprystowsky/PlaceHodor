/*
 * Copyright 2013 Jacob Miles Prystowsky
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.mapping.api.placehodor;

import java.util.Iterator;
import java.util.Random;

/**
 * A factory for generating a gibberish sentence based on an arbitrary lexicon and template factory.
 */
public class GibberishSentenceFactory extends SentenceFactory {
	private static final Random RANDOM = new Random();

	@Override
	public String getSentence() {
		String sentence = String.valueOf("");

		String[] nouns = getLexiconFactory().getNouns().toArray(new String[0]);
		String[] verbs = getLexiconFactory().getVerbs().toArray(new String[0]);
		String[] adjectives = getLexiconFactory().getAdjectives().toArray(new String[0]);
		String[] adverbs = getLexiconFactory().getAdverbs().toArray(new String[0]);

		Iterable<Object> template = getSentenceTemplateFactory().getSentenceTemplate(30);

		for (Iterator templateIterator = template.iterator(); templateIterator.hasNext(); ) {
			Object nextSentenceToken = templateIterator.next();

			if (nextSentenceToken instanceof String) {
				sentence += nextSentenceToken;
			} else {
				if (nextSentenceToken.equals(SentenceComponents.Noun.class)) {
					sentence += getTokenSeparator(sentence) + nouns[RANDOM.nextInt(nouns.length)];
				} else if (nextSentenceToken.equals(SentenceComponents.Verb.class)) {
					sentence += getTokenSeparator(sentence) + verbs[RANDOM.nextInt(verbs.length)];
				} else if (nextSentenceToken.equals(SentenceComponents.Adjective.class)) {
					sentence += getTokenSeparator(sentence) + adjectives[RANDOM.nextInt(adjectives.length)];
				} else if (nextSentenceToken.equals(SentenceComponents.Adverb.class)) {
					sentence += getTokenSeparator(sentence) + adverbs[RANDOM.nextInt(adverbs.length)];
				}
			}
		}

		return sentence;
	}

	/**
	 * Gets the token to separate multiple sentence tokens
	 *
	 * @param sentence the sentence as is
	 * @return a separator token
	 */
	private String getTokenSeparator(String sentence) {
		return (sentence.equals("") ? "" : " ");
	}
}
