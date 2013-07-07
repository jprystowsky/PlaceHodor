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

/**
 * A factory for creating sentence templates.
 */
public abstract class SentenceTemplateFactory {
	// Non-word middle sentence components
	protected final String[] sentenceMiddles = new String[]{
			" -- ", "; ", " ... "
	};

	// End components of a sentence
	protected final String[] sentenceEnds = new String[]{
			".", "?", "!", "?!", "!?"
	};

	/**
	 * Returns a new sentence component, based optionally on the previous sentence component.
	 *
	 * @param lastComponent the previous sentence component, or null if none (or if one shouldn't be considered)
	 * @return a new sentence component
	 */
	protected abstract Object getSentenceComponent(Object lastComponent);

	/**
	 * Creates a sentence template using logic delegated to concrete implementations.
	 *
	 * @param sentenceLength the maximum number of words
	 * @return a sentence template
	 */
	public abstract Iterable<Object> getSentenceTemplate(int sentenceLength);
}
