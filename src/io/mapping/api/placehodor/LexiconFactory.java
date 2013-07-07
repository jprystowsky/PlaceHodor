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

import java.util.Set;

/**
 * A factory for generating lexicons (e.g., nouns, verbs, etc.).
 */
public interface LexiconFactory {
	/**
	 * Returns the nouns in the lexicon.
	 *
	 * @return a set of nouns
	 */
	public Set<String> getNouns();

	/**
	 * Returns the verbs in the lexicon.
	 *
	 * @return a set of verbs
	 */
	public Set<String> getVerbs();

	/**
	 * Returns the adjectives in the lexicon.
	 *
	 * @return a set of adjectives
	 */
	public Set<String> getAdjectives();

	/**
	 * Returns the adverbs in the lexicon.
	 *
	 * @return a set of adverbs
	 */
	public Set<String> getAdverbs();
}
