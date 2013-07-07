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

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * A factory for creating sentence templates randomly (and hence ungrammatically).
 */
public class RandomSentenceTemplateFactory extends SentenceTemplateFactory {
	private static final Random RANDOM = new Random();

	@Override
	protected Object getSentenceComponent(Object lastComponent) {
		// Lexicon objects
		Object[] classTypes = new Object[]{
				SentenceComponents.Noun.class, SentenceComponents.Verb.class, SentenceComponents.Adjective.class, SentenceComponents.Adverb.class
		};

		// Lexicon objects and mid-sentence objects
		Object[] types = new Object[]{
				SentenceComponents.Noun.class, SentenceComponents.Verb.class, SentenceComponents.Adjective.class, SentenceComponents.Adverb.class,
				sentenceMiddles
		};

		Object nextComponent = null;

		if (lastComponent == null || lastComponent instanceof String[]) {
			// Always start with a class type, and don't stick two mid-sentence types together
			nextComponent = types[RANDOM.nextInt(classTypes.length)];
		} else {
			nextComponent = types[RANDOM.nextInt(types.length)];
		}

		if (nextComponent instanceof String[]) {
			// Sentence middles and other arrays of primitive values
			String[] typeArray = (String[]) nextComponent;
			return typeArray[RANDOM.nextInt(typeArray.length)];
		} else {
			// Class types
			return nextComponent;
		}
	}

	@Override
	public Iterable<Object> getSentenceTemplate(int sentenceLength) {
		List<Object> template = new LinkedList<>();

		// We need at least one word and an end component
		int length = RANDOM.nextInt(Math.max(0, sentenceLength - 2)) + 2;

		Object nextToken = null;
		for (int i = 0; i < length - 1; i++) {
			nextToken = getSentenceComponent(nextToken);
			template.add(nextToken);
		}

		// End of the sentence
		template.add(sentenceEnds[RANDOM.nextInt(sentenceEnds.length)]);

		return template;
	}
}
