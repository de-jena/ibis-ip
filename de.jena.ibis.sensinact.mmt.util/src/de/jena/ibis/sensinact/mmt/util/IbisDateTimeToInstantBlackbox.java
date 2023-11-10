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
package de.jena.ibis.sensinact.mmt.util;

import java.time.Instant;

import javax.xml.datatype.XMLGregorianCalendar;

import org.eclipse.m2m.qvt.oml.blackbox.java.Module;
import org.eclipse.m2m.qvt.oml.blackbox.java.Operation;
import org.gecko.qvt.osgi.api.ModelTransformationConstants;
import org.osgi.service.component.annotations.Component;

import de.jena.model.ibis.common.IBISIPDate;
import de.jena.model.ibis.common.IBISIPDateTime;
import de.jena.model.ibis.common.IBISIPTime;
import de.jena.model.ibis.common.IbisCommonPackage;

@Component(service = IbisDateTimeToInstantBlackbox.class, immediate=true, 
property = {ModelTransformationConstants.QVT_BLACKBOX + "=true", 
		  ModelTransformationConstants.BLACKBOX_MODULENAME + "=IbisDateTimeToInstant", 
		  ModelTransformationConstants.BLACKBOX_QUALIFIED_UNIT_NAME + "=de.jena.ibis.sensinact.mmt.util.IbisDateTimeToInstantBlackbox"})
@Module(packageURIs={IbisCommonPackage.eNS_URI, "https://eclipse.org/sensinact/core/provider/1.0"})
public class IbisDateTimeToInstantBlackbox {
	
	@Operation(description = "Converts from IBISIPDateTime into milliseconds")
	public Long getMillis(IBISIPDateTime ibisDateTime) {
		if(ibisDateTime != null) {			
			return getMillis(ibisDateTime.getValue());	
		}
		return null;
	}
	
	@Operation(description = "Converts from IBISIPDate into milliseconds")
	public Long getMillis(IBISIPDate ibisDate) {
		if(ibisDate != null) {			
			return getMillis(ibisDate.getValue());	
		}
		return null;
	}
	
	@Operation(description = "Converts from IBISIPTime into milliseconds")
	public Long getMillis(IBISIPTime ibisTime) {
		if(ibisTime != null) {			
			return getMillis(ibisTime.getValue());	
		}
		return null;
	}
	
	@Operation(description = "Converts from milliseconds to Instant")
	public Instant getInstant(Long millis) {
		if(millis !=  null) {
			return Instant.ofEpochMilli(millis);
		}
		return null;
	}
	
	private Long getMillis(XMLGregorianCalendar xmlCalendar) {
		return xmlCalendar.toGregorianCalendar().getTimeInMillis();
	}
}
