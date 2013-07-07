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

package io.mapping.api.placehodor.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import io.mapping.api.placehodor.*;
import io.mapping.api.placehodor.resources.Sentence;

public class GuiceConfig extends GuiceServletContextListener {
	@Override
	protected Injector getInjector() {
		return Guice.createInjector(new JerseyServletModule() {
			@Override
			protected void configureServlets() {
				// Resource bindings
				bind(Sentence.class);

				// Dependency injection
				bind(LexiconFactory.class).to(HodorLexiconFactory.class);

				bind(SentenceTemplateFactory.class).to(RandomSentenceTemplateFactory.class);

				bind(SentenceFactory.class).to(GibberishSentenceFactory.class);

				serve("/*").with(GuiceContainer.class);
			}
		});
	}
}
