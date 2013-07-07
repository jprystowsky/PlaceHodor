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

package io.mapping.api.placehodor.resources;

import com.google.gson.Gson;
import com.google.inject.Inject;
import io.mapping.api.placehodor.SentenceFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.LinkedList;
import java.util.List;

@Path("/sentence")
public class Sentence {
	@Inject
	SentenceFactory mSentenceFactory;
	@Inject
	Gson mGson;

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getJSONSentence() {
		return mGson.toJson(mSentenceFactory.getSentence());
	}

	@GET
	@Path("/{quantity}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getJSONSentence(@PathParam("quantity") int quantity) {
		List<String> sentences = new LinkedList<>();

		for (int i = 0; i < quantity; i++) {
			sentences.add(mSentenceFactory.getSentence());
		}

		return mGson.toJson(sentences);
	}
}
