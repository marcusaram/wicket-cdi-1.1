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

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import com.github.javawithmarcus.wicket.cdi.WicketApp;

/**
 * @author jsarman
 */
@WicketApp("test2")
public class TestCdiAdditionApplication extends WebApplication
{

	@Inject
	@TestQualifier
	String testString;


	@Override
	public Class<? extends Page> getHomePage()
	{
		return TestPage.class;
	}


	@PostConstruct
	@Override
	protected void init()
	{
		super.init();

	}

	public String getInjectedTestString()
	{
		return testString;
	}

}