/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 *
 */

package org.apache.openaz.xacml.admin.view.windows;

import oasis.names.tc.xacml._3_0.core.schema.wd_17.AdviceExpressionType;
import oasis.names.tc.xacml._3_0.core.schema.wd_17.EffectType;

import org.apache.openaz.xacml.admin.XacmlAdminUI;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Buffered.SourceException;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class AdviceEditorWindow extends Window {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private Button buttonSave;
	@AutoGenerated
	private OptionGroup optionGroupEffect;
	@AutoGenerated
	private TextField textFieldAdviceID;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final AdviceEditorWindow self = this;
	private final AdviceExpressionType advice;
	private boolean isSaved = false;
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public AdviceEditorWindow(AdviceExpressionType advice) {
		buildMainLayout();
		//setCompositionRoot(mainLayout);
		setContent(mainLayout);
		//
		// Save
		//
		this.advice = advice;
		//
		// Set our shortcuts
		//
		this.setCloseShortcut(KeyCode.ESCAPE);
		//
		// Initialize GUI
		//
		this.initialize();
		this.initializeButton();
		//
		// Focus
		//
		this.textFieldAdviceID.focus();
	}
	
	protected void initialize() {
		//
		// The text field for the advice ID
		//
		this.textFieldAdviceID.setNullRepresentation("");
		if (this.advice.getAdviceId() == null) {
			this.textFieldAdviceID.setValue(XacmlAdminUI.getDomain());
		} else {
			this.textFieldAdviceID.setValue(advice.getAdviceId());
		}
		this.textFieldAdviceID.setRequiredError("You must have an ID for the advice");
		//
		// The option
		//
		this.optionGroupEffect.setRequiredError("You must select Permit or Deny for the advice");
		this.optionGroupEffect.addItem(EffectType.PERMIT);
		this.optionGroupEffect.addItem(EffectType.DENY);
		if (this.advice.getAppliesTo() == null) {
			this.optionGroupEffect.select(EffectType.PERMIT);
		} else {
			if (this.advice.getAppliesTo().equals(EffectType.PERMIT)) {
				this.optionGroupEffect.select(EffectType.PERMIT);
			} else {
				this.optionGroupEffect.select(EffectType.DENY);
			}
		}
	}
	
	protected void initializeButton() {
		this.buttonSave.setImmediate(true);
		this.buttonSave.setClickShortcut(KeyCode.ENTER);
		this.buttonSave.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				try {
					//
					// Commit
					//
					self.textFieldAdviceID.commit();
					self.optionGroupEffect.commit();
					//
					// all good, save everything
					//
					self.advice.setAdviceId(self.textFieldAdviceID.getValue());
					self.advice.setAppliesTo((EffectType) self.optionGroupEffect.getValue());
					//
					// Set ourselves as saved
					//
					self.isSaved = true;
					//
					// Close the window
					//
					self.close();
				} catch (SourceException | InvalidValueException e) { //NOPMD
					//
					// Vaadin displays the error
					//
				}
			}			
		});
	}
	
	public boolean isSaved() {
		return this.isSaved;
	}
	
	public AdviceExpressionType getAdvice() {
		return this.advice;
	}

	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("-1px");
		mainLayout.setHeight("-1px");
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);
		
		// top-level component properties
		setWidth("-1px");
		setHeight("-1px");
		
		// textFieldAdviceID
		textFieldAdviceID = new TextField();
		textFieldAdviceID.setCaption("Advice ID");
		textFieldAdviceID.setImmediate(false);
		textFieldAdviceID.setWidth("-1px");
		textFieldAdviceID.setHeight("-1px");
		textFieldAdviceID.setInvalidAllowed(false);
		textFieldAdviceID.setRequired(true);
		textFieldAdviceID.setInputPrompt("Eg. urn:com:foo:advice:sample");
		mainLayout.addComponent(textFieldAdviceID);
		
		// optionGroupEffect
		optionGroupEffect = new OptionGroup();
		optionGroupEffect.setCaption("Applies To");
		optionGroupEffect.setImmediate(false);
		optionGroupEffect.setWidth("-1px");
		optionGroupEffect.setHeight("-1px");
		optionGroupEffect.setInvalidAllowed(false);
		optionGroupEffect.setRequired(true);
		mainLayout.addComponent(optionGroupEffect);
		
		// buttonSave
		buttonSave = new Button();
		buttonSave.setCaption("Save");
		buttonSave.setImmediate(true);
		buttonSave.setWidth("-1px");
		buttonSave.setHeight("-1px");
		mainLayout.addComponent(buttonSave);
		mainLayout.setComponentAlignment(buttonSave, new Alignment(48));
		
		return mainLayout;
	}

}
