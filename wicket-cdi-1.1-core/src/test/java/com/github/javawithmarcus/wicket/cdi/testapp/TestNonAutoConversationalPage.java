/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.javawithmarcus.wicket.cdi.testapp;

import java.util.Random;
import javax.enterprise.context.Conversation;
import javax.inject.Inject;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.github.javawithmarcus.wicket.cdi.ConversationPropagation;
import com.github.javawithmarcus.wicket.cdi.Conversational;


/**
 * @author jsarman
 */
@Conversational(auto = false, prop = ConversationPropagation.ALL)
public class TestNonAutoConversationalPage extends WebPage
{
	private static final Logger logger = LoggerFactory.getLogger(TestConversationPage.class);
	@Inject
	TestConversationBean counter;

	@Inject
	Conversation conversation;

	Random random = new Random();

	public TestNonAutoConversationalPage()
	{
		this(new PageParameters());
	}


	public TestNonAutoConversationalPage(final PageParameters pp)
	{
		if (pp.get("startConveration").toBoolean(true))
		{
			conversation.begin();
		}
		logger.debug("Starting TestConversationalPage");

		add(new Label("count", new PropertyModel(this, "counter.count")));

		add(new Link<Void>("increment")
		{
			@Override
			public void onClick()
			{
				counter.increment();
			}
		});
		add(new Link<Void>("next")
		{
			@Override
			public void onClick()
			{
				String pageType = pp.get("pageType").toString("nonbookmarkable");
				switch (pageType.toLowerCase())
				{
					case "bookmarkable":
						setResponsePage(TestNonConversationalPage.class);
						break;
					default:
						setResponsePage(new TestNonConversationalPage());
				}

			}
		});
	}
}