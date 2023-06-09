/*
 * Copyright (c) 2022 Contributors to the Eclipse Foundation.
 * 
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *   Data In Motion - initial API and implementation 
 */
package de.jena.model.sensinact.ibis;

import org.eclipse.emf.ecore.EFactory;

import org.osgi.annotation.versioning.ProviderType;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see de.jena.model.sensinact.ibis.IbisSensinactPackage
 * @generated
 */
@ProviderType
public interface IbisSensinactFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	IbisSensinactFactory eINSTANCE = de.jena.model.sensinact.ibis.impl.IbisSensinactFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Ibis Device</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Ibis Device</em>'.
	 * @generated
	 */
	IbisDevice createIbisDevice();

	/**
	 * Returns a new object of class '<em>Customer Info All Data</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Customer Info All Data</em>'.
	 * @generated
	 */
	CustomerInfoAllData createCustomerInfoAllData();

	/**
	 * Returns a new object of class '<em>Customer Info Current Stop Index Data</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Customer Info Current Stop Index Data</em>'.
	 * @generated
	 */
	CustomerInfoCurrentStopIndexData createCustomerInfoCurrentStopIndexData();

	/**
	 * Returns a new object of class '<em>Customer Info Current Stop Point Data</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Customer Info Current Stop Point Data</em>'.
	 * @generated
	 */
	CustomerInfoCurrentStopPointData createCustomerInfoCurrentStopPointData();

	/**
	 * Returns a new object of class '<em>Customer Info Trip Data</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Customer Info Trip Data</em>'.
	 * @generated
	 */
	CustomerInfoTripData createCustomerInfoTripData();

	/**
	 * Returns a new object of class '<em>Customer Info Vehicle Data</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Customer Info Vehicle Data</em>'.
	 * @generated
	 */
	CustomerInfoVehicleData createCustomerInfoVehicleData();

	/**
	 * Returns a new object of class '<em>Customer Info Current Announcement Data</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Customer Info Current Announcement Data</em>'.
	 * @generated
	 */
	CustomerInfoCurrentAnnouncementData createCustomerInfoCurrentAnnouncementData();

	/**
	 * Returns a new object of class '<em>Customer Info Current Connection Data</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Customer Info Current Connection Data</em>'.
	 * @generated
	 */
	CustomerInfoCurrentConnectionData createCustomerInfoCurrentConnectionData();

	/**
	 * Returns a new object of class '<em>Customer Info Current Display Content Data</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Customer Info Current Display Content Data</em>'.
	 * @generated
	 */
	CustomerInfoCurrentDisplayContentData createCustomerInfoCurrentDisplayContentData();

	/**
	 * Returns a new object of class '<em>Ibis Admin</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Ibis Admin</em>'.
	 * @generated
	 */
	IbisAdmin createIbisAdmin();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	IbisSensinactPackage getIbisSensinactPackage();

} //IbisSensinactFactory
