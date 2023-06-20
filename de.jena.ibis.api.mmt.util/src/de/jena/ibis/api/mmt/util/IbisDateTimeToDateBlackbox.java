/**
 * Copyright (c) 2012 - 2018 Data In Motion and others.
 * All rights reserved. 
 * 
 * This program and the accompanying materials are made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Data In Motion - initial API and implementation
 */
package de.jena.ibis.api.mmt.util;

import java.util.Date;

import org.eclipse.m2m.qvt.oml.blackbox.java.Module;
import org.eclipse.m2m.qvt.oml.blackbox.java.Operation;
import org.gecko.qvt.osgi.api.ModelTransformationConstants;
import org.osgi.service.component.annotations.Component;

import de.jena.model.ibis.common.IBISIPDateTime;
import de.jena.model.ibis.common.IbisCommonPackage;

@Component(service = IbisDateTimeToDateBlackbox.class, immediate=true, 
property = {ModelTransformationConstants.QVT_BLACKBOX + "=true", 
		  ModelTransformationConstants.BLACKBOX_MODULENAME + "=IbisDateTimeToDate", 
		  ModelTransformationConstants.BLACKBOX_QUALIFIED_UNIT_NAME + "=de.jena.ibis.api.mmt.util.IbisDateTimeToDateBlackbox"})
@Module(packageURIs={IbisCommonPackage.eNS_URI, "http://www.eclipse.org/emf/2002/Ecore"})
public class IbisDateTimeToDateBlackbox {

	@Operation(description = "Converts from IBISIPDateTime into milliseconds")
	public Long getMillis(IBISIPDateTime ibisDateTime) {
		if(ibisDateTime != null) {			
			return ibisDateTime.getValue().toGregorianCalendar().getTimeInMillis();	
		}
		return null;
	}
	
	@Operation(description = "Converts from IBISIPDateTime into java.util.Date")
	public Date getDate(IBISIPDateTime ibisDateTime) {
		if(ibisDateTime != null) {	
			return new Date(getMillis(ibisDateTime));
		}
		return null;
	}

}
