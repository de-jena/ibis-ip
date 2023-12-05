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


import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.sensinact.model.core.provider.ProviderPackage;

import org.gecko.emf.osgi.annotation.provide.EPackage;

import org.osgi.annotation.versioning.ProviderType;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see de.jena.model.sensinact.ibis.IbisSensinactFactory
 * @model kind="package"
 *        annotation="http://www.eclipse.org/OCL/Import ecore='http://www.eclipse.org/emf/2002/Ecore'"
 * @generated
 */
@ProviderType
@EPackage(uri = IbisSensinactPackage.eNS_URI, genModel = "/model/ibis.genmodel", genModelSourceLocations = {"model/ibis.genmodel","de.jena.ibis.sensinact.model/model/ibis.genmodel"}, ecore="/model/ibis.ecore", ecoreSourceLocations="/model/ibis.ecore")
public interface IbisSensinactPackage extends org.eclipse.emf.ecore.EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "ibis";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "https://jena.de/models/ibis/sensinact/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "ibis";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	IbisSensinactPackage eINSTANCE = de.jena.model.sensinact.ibis.impl.IbisSensinactPackageImpl.init();

	/**
	 * The meta object id for the '{@link de.jena.model.sensinact.ibis.impl.IbisDeviceImpl <em>Ibis Device</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.jena.model.sensinact.ibis.impl.IbisDeviceImpl
	 * @see de.jena.model.sensinact.ibis.impl.IbisSensinactPackageImpl#getIbisDevice()
	 * @generated
	 */
	int IBIS_DEVICE = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IBIS_DEVICE__ID = ProviderPackage.PROVIDER__ID;

	/**
	 * The feature id for the '<em><b>Admin</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IBIS_DEVICE__ADMIN = ProviderPackage.PROVIDER__ADMIN;

	/**
	 * The feature id for the '<em><b>Linked Providers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IBIS_DEVICE__LINKED_PROVIDERS = ProviderPackage.PROVIDER__LINKED_PROVIDERS;

	/**
	 * The feature id for the '<em><b>Customer Info All Data</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IBIS_DEVICE__CUSTOMER_INFO_ALL_DATA = ProviderPackage.PROVIDER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Customer Info Current Stop Index Data</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IBIS_DEVICE__CUSTOMER_INFO_CURRENT_STOP_INDEX_DATA = ProviderPackage.PROVIDER_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Customer Info Current Stop Point Data</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IBIS_DEVICE__CUSTOMER_INFO_CURRENT_STOP_POINT_DATA = ProviderPackage.PROVIDER_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Customer Info Trip Data</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IBIS_DEVICE__CUSTOMER_INFO_TRIP_DATA = ProviderPackage.PROVIDER_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Customer Info Vehicle Data</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IBIS_DEVICE__CUSTOMER_INFO_VEHICLE_DATA = ProviderPackage.PROVIDER_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Customer Info Current Announcement Data</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IBIS_DEVICE__CUSTOMER_INFO_CURRENT_ANNOUNCEMENT_DATA = ProviderPackage.PROVIDER_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Customer Info Current Connection Data</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IBIS_DEVICE__CUSTOMER_INFO_CURRENT_CONNECTION_DATA = ProviderPackage.PROVIDER_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Customer Info Current Display Content Data</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IBIS_DEVICE__CUSTOMER_INFO_CURRENT_DISPLAY_CONTENT_DATA = ProviderPackage.PROVIDER_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Ibis Admin</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IBIS_DEVICE__IBIS_ADMIN = ProviderPackage.PROVIDER_FEATURE_COUNT + 8;

	/**
	 * The number of structural features of the '<em>Ibis Device</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IBIS_DEVICE_FEATURE_COUNT = ProviderPackage.PROVIDER_FEATURE_COUNT + 9;

	/**
	 * The number of operations of the '<em>Ibis Device</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IBIS_DEVICE_OPERATION_COUNT = ProviderPackage.PROVIDER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.jena.model.sensinact.ibis.impl.CustomerInfoAllDataImpl <em>Customer Info All Data</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.jena.model.sensinact.ibis.impl.CustomerInfoAllDataImpl
	 * @see de.jena.model.sensinact.ibis.impl.IbisSensinactPackageImpl#getCustomerInfoAllData()
	 * @generated
	 */
	int CUSTOMER_INFO_ALL_DATA = 1;

	/**
	 * The feature id for the '<em><b>Metadata</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_ALL_DATA__METADATA = ProviderPackage.SERVICE__METADATA;

	/**
	 * The feature id for the '<em><b>Service Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_ALL_DATA__SERVICE_NAME = ProviderPackage.SERVICE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Service Operation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_ALL_DATA__SERVICE_OPERATION = ProviderPackage.SERVICE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Timestamp</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_ALL_DATA__TIMESTAMP = ProviderPackage.SERVICE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Vehicle Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_ALL_DATA__VEHICLE_REF = ProviderPackage.SERVICE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Default Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_ALL_DATA__DEFAULT_LANGUAGE = ProviderPackage.SERVICE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Current Stop Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_ALL_DATA__CURRENT_STOP_INDEX = ProviderPackage.SERVICE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Route Deviation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_ALL_DATA__ROUTE_DEVIATION = ProviderPackage.SERVICE_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Door State</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_ALL_DATA__DOOR_STATE = ProviderPackage.SERVICE_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>In Panic</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_ALL_DATA__IN_PANIC = ProviderPackage.SERVICE_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Vehicle Stop Requested</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_ALL_DATA__VEHICLE_STOP_REQUESTED = ProviderPackage.SERVICE_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Exit Side</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_ALL_DATA__EXIT_SIDE = ProviderPackage.SERVICE_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Moving Direction Forward</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_ALL_DATA__MOVING_DIRECTION_FORWARD = ProviderPackage.SERVICE_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Vehicle Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_ALL_DATA__VEHICLE_MODE = ProviderPackage.SERVICE_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>Speaker Active</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_ALL_DATA__SPEAKER_ACTIVE = ProviderPackage.SERVICE_FEATURE_COUNT + 13;

	/**
	 * The feature id for the '<em><b>Stop Information Active</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_ALL_DATA__STOP_INFORMATION_ACTIVE = ProviderPackage.SERVICE_FEATURE_COUNT + 14;

	/**
	 * The feature id for the '<em><b>Trip State</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_ALL_DATA__TRIP_STATE = ProviderPackage.SERVICE_FEATURE_COUNT + 15;

	/**
	 * The number of structural features of the '<em>Customer Info All Data</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_ALL_DATA_FEATURE_COUNT = ProviderPackage.SERVICE_FEATURE_COUNT + 16;

	/**
	 * The operation id for the '<em>EIs Set</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_ALL_DATA___EIS_SET__ESTRUCTURALFEATURE = ProviderPackage.SERVICE___EIS_SET__ESTRUCTURALFEATURE;

	/**
	 * The number of operations of the '<em>Customer Info All Data</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_ALL_DATA_OPERATION_COUNT = ProviderPackage.SERVICE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.jena.model.sensinact.ibis.impl.CustomerInfoCurrentStopIndexDataImpl <em>Customer Info Current Stop Index Data</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.jena.model.sensinact.ibis.impl.CustomerInfoCurrentStopIndexDataImpl
	 * @see de.jena.model.sensinact.ibis.impl.IbisSensinactPackageImpl#getCustomerInfoCurrentStopIndexData()
	 * @generated
	 */
	int CUSTOMER_INFO_CURRENT_STOP_INDEX_DATA = 2;

	/**
	 * The feature id for the '<em><b>Metadata</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_CURRENT_STOP_INDEX_DATA__METADATA = ProviderPackage.SERVICE__METADATA;

	/**
	 * The feature id for the '<em><b>Service Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_CURRENT_STOP_INDEX_DATA__SERVICE_NAME = ProviderPackage.SERVICE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Service Operation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_CURRENT_STOP_INDEX_DATA__SERVICE_OPERATION = ProviderPackage.SERVICE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Timestamp</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_CURRENT_STOP_INDEX_DATA__TIMESTAMP = ProviderPackage.SERVICE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Current Stop Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_CURRENT_STOP_INDEX_DATA__CURRENT_STOP_INDEX = ProviderPackage.SERVICE_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Customer Info Current Stop Index Data</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_CURRENT_STOP_INDEX_DATA_FEATURE_COUNT = ProviderPackage.SERVICE_FEATURE_COUNT + 4;

	/**
	 * The operation id for the '<em>EIs Set</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_CURRENT_STOP_INDEX_DATA___EIS_SET__ESTRUCTURALFEATURE = ProviderPackage.SERVICE___EIS_SET__ESTRUCTURALFEATURE;

	/**
	 * The number of operations of the '<em>Customer Info Current Stop Index Data</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_CURRENT_STOP_INDEX_DATA_OPERATION_COUNT = ProviderPackage.SERVICE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.jena.model.sensinact.ibis.impl.CustomerInfoCurrentStopPointDataImpl <em>Customer Info Current Stop Point Data</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.jena.model.sensinact.ibis.impl.CustomerInfoCurrentStopPointDataImpl
	 * @see de.jena.model.sensinact.ibis.impl.IbisSensinactPackageImpl#getCustomerInfoCurrentStopPointData()
	 * @generated
	 */
	int CUSTOMER_INFO_CURRENT_STOP_POINT_DATA = 3;

	/**
	 * The feature id for the '<em><b>Metadata</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_CURRENT_STOP_POINT_DATA__METADATA = ProviderPackage.SERVICE__METADATA;

	/**
	 * The feature id for the '<em><b>Service Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_CURRENT_STOP_POINT_DATA__SERVICE_NAME = ProviderPackage.SERVICE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Service Operation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_CURRENT_STOP_POINT_DATA__SERVICE_OPERATION = ProviderPackage.SERVICE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Timestamp</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_CURRENT_STOP_POINT_DATA__TIMESTAMP = ProviderPackage.SERVICE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Stop Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_CURRENT_STOP_POINT_DATA__STOP_INDEX = ProviderPackage.SERVICE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Stop Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_CURRENT_STOP_POINT_DATA__STOP_REF = ProviderPackage.SERVICE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Stop Name</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_CURRENT_STOP_POINT_DATA__STOP_NAME = ProviderPackage.SERVICE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Stop Alternative Name</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_CURRENT_STOP_POINT_DATA__STOP_ALTERNATIVE_NAME = ProviderPackage.SERVICE_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Platform</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_CURRENT_STOP_POINT_DATA__PLATFORM = ProviderPackage.SERVICE_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Arrival Scheduled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_CURRENT_STOP_POINT_DATA__ARRIVAL_SCHEDULED = ProviderPackage.SERVICE_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Arrival Expected</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_CURRENT_STOP_POINT_DATA__ARRIVAL_EXPECTED = ProviderPackage.SERVICE_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Departure Scheduled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_CURRENT_STOP_POINT_DATA__DEPARTURE_SCHEDULED = ProviderPackage.SERVICE_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Departure Expected</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_CURRENT_STOP_POINT_DATA__DEPARTURE_EXPECTED = ProviderPackage.SERVICE_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Recorded Arrival Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_CURRENT_STOP_POINT_DATA__RECORDED_ARRIVAL_TIME = ProviderPackage.SERVICE_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>Distance To Next Stop</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_CURRENT_STOP_POINT_DATA__DISTANCE_TO_NEXT_STOP = ProviderPackage.SERVICE_FEATURE_COUNT + 13;

	/**
	 * The feature id for the '<em><b>Fare Zone</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_CURRENT_STOP_POINT_DATA__FARE_ZONE = ProviderPackage.SERVICE_FEATURE_COUNT + 14;

	/**
	 * The number of structural features of the '<em>Customer Info Current Stop Point Data</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_CURRENT_STOP_POINT_DATA_FEATURE_COUNT = ProviderPackage.SERVICE_FEATURE_COUNT + 15;

	/**
	 * The operation id for the '<em>EIs Set</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_CURRENT_STOP_POINT_DATA___EIS_SET__ESTRUCTURALFEATURE = ProviderPackage.SERVICE___EIS_SET__ESTRUCTURALFEATURE;

	/**
	 * The number of operations of the '<em>Customer Info Current Stop Point Data</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_CURRENT_STOP_POINT_DATA_OPERATION_COUNT = ProviderPackage.SERVICE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.jena.model.sensinact.ibis.impl.CustomerInfoTripDataImpl <em>Customer Info Trip Data</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.jena.model.sensinact.ibis.impl.CustomerInfoTripDataImpl
	 * @see de.jena.model.sensinact.ibis.impl.IbisSensinactPackageImpl#getCustomerInfoTripData()
	 * @generated
	 */
	int CUSTOMER_INFO_TRIP_DATA = 4;

	/**
	 * The feature id for the '<em><b>Metadata</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_TRIP_DATA__METADATA = ProviderPackage.SERVICE__METADATA;

	/**
	 * The feature id for the '<em><b>Service Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_TRIP_DATA__SERVICE_NAME = ProviderPackage.SERVICE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Service Operation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_TRIP_DATA__SERVICE_OPERATION = ProviderPackage.SERVICE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Timestamp</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_TRIP_DATA__TIMESTAMP = ProviderPackage.SERVICE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Vehicle Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_TRIP_DATA__VEHICLE_REF = ProviderPackage.SERVICE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Default Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_TRIP_DATA__DEFAULT_LANGUAGE = ProviderPackage.SERVICE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Current Stop Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_TRIP_DATA__CURRENT_STOP_INDEX = ProviderPackage.SERVICE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Trip Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_TRIP_DATA__TRIP_REF = ProviderPackage.SERVICE_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Location State</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_TRIP_DATA__LOCATION_STATE = ProviderPackage.SERVICE_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Timetable Delay</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_TRIP_DATA__TIMETABLE_DELAY = ProviderPackage.SERVICE_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Route Direction</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_TRIP_DATA__ROUTE_DIRECTION = ProviderPackage.SERVICE_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Run Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_TRIP_DATA__RUN_NUMBER = ProviderPackage.SERVICE_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Pattern Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_TRIP_DATA__PATTERN_NUMBER = ProviderPackage.SERVICE_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Path Destination Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_TRIP_DATA__PATH_DESTINATION_NUMBER = ProviderPackage.SERVICE_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>Additional Text Msg</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_TRIP_DATA__ADDITIONAL_TEXT_MSG = ProviderPackage.SERVICE_FEATURE_COUNT + 13;

	/**
	 * The feature id for the '<em><b>Additional Text Msg1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_TRIP_DATA__ADDITIONAL_TEXT_MSG1 = ProviderPackage.SERVICE_FEATURE_COUNT + 14;

	/**
	 * The feature id for the '<em><b>Additional Text Msg2</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_TRIP_DATA__ADDITIONAL_TEXT_MSG2 = ProviderPackage.SERVICE_FEATURE_COUNT + 15;

	/**
	 * The feature id for the '<em><b>Additional Text Msg3</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_TRIP_DATA__ADDITIONAL_TEXT_MSG3 = ProviderPackage.SERVICE_FEATURE_COUNT + 16;

	/**
	 * The feature id for the '<em><b>Additional Text Msg4</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_TRIP_DATA__ADDITIONAL_TEXT_MSG4 = ProviderPackage.SERVICE_FEATURE_COUNT + 17;

	/**
	 * The feature id for the '<em><b>Additional Text Msg5</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_TRIP_DATA__ADDITIONAL_TEXT_MSG5 = ProviderPackage.SERVICE_FEATURE_COUNT + 18;

	/**
	 * The feature id for the '<em><b>Additional Text Msg6</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_TRIP_DATA__ADDITIONAL_TEXT_MSG6 = ProviderPackage.SERVICE_FEATURE_COUNT + 19;

	/**
	 * The feature id for the '<em><b>Additional Text Msg7</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_TRIP_DATA__ADDITIONAL_TEXT_MSG7 = ProviderPackage.SERVICE_FEATURE_COUNT + 20;

	/**
	 * The feature id for the '<em><b>Additional Text Msg8</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_TRIP_DATA__ADDITIONAL_TEXT_MSG8 = ProviderPackage.SERVICE_FEATURE_COUNT + 21;

	/**
	 * The feature id for the '<em><b>Additional Text Msg9</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_TRIP_DATA__ADDITIONAL_TEXT_MSG9 = ProviderPackage.SERVICE_FEATURE_COUNT + 22;

	/**
	 * The number of structural features of the '<em>Customer Info Trip Data</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_TRIP_DATA_FEATURE_COUNT = ProviderPackage.SERVICE_FEATURE_COUNT + 23;

	/**
	 * The operation id for the '<em>EIs Set</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_TRIP_DATA___EIS_SET__ESTRUCTURALFEATURE = ProviderPackage.SERVICE___EIS_SET__ESTRUCTURALFEATURE;

	/**
	 * The number of operations of the '<em>Customer Info Trip Data</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_TRIP_DATA_OPERATION_COUNT = ProviderPackage.SERVICE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.jena.model.sensinact.ibis.impl.CustomerInfoVehicleDataImpl <em>Customer Info Vehicle Data</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.jena.model.sensinact.ibis.impl.CustomerInfoVehicleDataImpl
	 * @see de.jena.model.sensinact.ibis.impl.IbisSensinactPackageImpl#getCustomerInfoVehicleData()
	 * @generated
	 */
	int CUSTOMER_INFO_VEHICLE_DATA = 5;

	/**
	 * The feature id for the '<em><b>Metadata</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_VEHICLE_DATA__METADATA = ProviderPackage.SERVICE__METADATA;

	/**
	 * The feature id for the '<em><b>Service Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_VEHICLE_DATA__SERVICE_NAME = ProviderPackage.SERVICE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Service Operation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_VEHICLE_DATA__SERVICE_OPERATION = ProviderPackage.SERVICE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Timestamp</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_VEHICLE_DATA__TIMESTAMP = ProviderPackage.SERVICE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Vehicle Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_VEHICLE_DATA__VEHICLE_REF = ProviderPackage.SERVICE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Route Deviation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_VEHICLE_DATA__ROUTE_DEVIATION = ProviderPackage.SERVICE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Door State</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_VEHICLE_DATA__DOOR_STATE = ProviderPackage.SERVICE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>In Panic</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_VEHICLE_DATA__IN_PANIC = ProviderPackage.SERVICE_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Vehicle Stop Requested</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_VEHICLE_DATA__VEHICLE_STOP_REQUESTED = ProviderPackage.SERVICE_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Exit Side</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_VEHICLE_DATA__EXIT_SIDE = ProviderPackage.SERVICE_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Moving Direction Forward</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_VEHICLE_DATA__MOVING_DIRECTION_FORWARD = ProviderPackage.SERVICE_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Vehicle Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_VEHICLE_DATA__VEHICLE_MODE = ProviderPackage.SERVICE_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Speaker Active</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_VEHICLE_DATA__SPEAKER_ACTIVE = ProviderPackage.SERVICE_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Stop Information Active</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_VEHICLE_DATA__STOP_INFORMATION_ACTIVE = ProviderPackage.SERVICE_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>Trip State</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_VEHICLE_DATA__TRIP_STATE = ProviderPackage.SERVICE_FEATURE_COUNT + 13;

	/**
	 * The number of structural features of the '<em>Customer Info Vehicle Data</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_VEHICLE_DATA_FEATURE_COUNT = ProviderPackage.SERVICE_FEATURE_COUNT + 14;

	/**
	 * The operation id for the '<em>EIs Set</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_VEHICLE_DATA___EIS_SET__ESTRUCTURALFEATURE = ProviderPackage.SERVICE___EIS_SET__ESTRUCTURALFEATURE;

	/**
	 * The number of operations of the '<em>Customer Info Vehicle Data</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_VEHICLE_DATA_OPERATION_COUNT = ProviderPackage.SERVICE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.jena.model.sensinact.ibis.impl.CustomerInfoCurrentAnnouncementDataImpl <em>Customer Info Current Announcement Data</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.jena.model.sensinact.ibis.impl.CustomerInfoCurrentAnnouncementDataImpl
	 * @see de.jena.model.sensinact.ibis.impl.IbisSensinactPackageImpl#getCustomerInfoCurrentAnnouncementData()
	 * @generated
	 */
	int CUSTOMER_INFO_CURRENT_ANNOUNCEMENT_DATA = 6;

	/**
	 * The feature id for the '<em><b>Metadata</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_CURRENT_ANNOUNCEMENT_DATA__METADATA = ProviderPackage.SERVICE__METADATA;

	/**
	 * The feature id for the '<em><b>Service Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_CURRENT_ANNOUNCEMENT_DATA__SERVICE_NAME = ProviderPackage.SERVICE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Service Operation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_CURRENT_ANNOUNCEMENT_DATA__SERVICE_OPERATION = ProviderPackage.SERVICE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Timestamp</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_CURRENT_ANNOUNCEMENT_DATA__TIMESTAMP = ProviderPackage.SERVICE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Announcement Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_CURRENT_ANNOUNCEMENT_DATA__ANNOUNCEMENT_REF = ProviderPackage.SERVICE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Announcement Text</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_CURRENT_ANNOUNCEMENT_DATA__ANNOUNCEMENT_TEXT = ProviderPackage.SERVICE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Announcement TTS Text</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_CURRENT_ANNOUNCEMENT_DATA__ANNOUNCEMENT_TTS_TEXT = ProviderPackage.SERVICE_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Customer Info Current Announcement Data</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_CURRENT_ANNOUNCEMENT_DATA_FEATURE_COUNT = ProviderPackage.SERVICE_FEATURE_COUNT + 6;

	/**
	 * The operation id for the '<em>EIs Set</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_CURRENT_ANNOUNCEMENT_DATA___EIS_SET__ESTRUCTURALFEATURE = ProviderPackage.SERVICE___EIS_SET__ESTRUCTURALFEATURE;

	/**
	 * The number of operations of the '<em>Customer Info Current Announcement Data</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_CURRENT_ANNOUNCEMENT_DATA_OPERATION_COUNT = ProviderPackage.SERVICE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.jena.model.sensinact.ibis.impl.CustomerInfoCurrentConnectionDataImpl <em>Customer Info Current Connection Data</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.jena.model.sensinact.ibis.impl.CustomerInfoCurrentConnectionDataImpl
	 * @see de.jena.model.sensinact.ibis.impl.IbisSensinactPackageImpl#getCustomerInfoCurrentConnectionData()
	 * @generated
	 */
	int CUSTOMER_INFO_CURRENT_CONNECTION_DATA = 7;

	/**
	 * The feature id for the '<em><b>Metadata</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_CURRENT_CONNECTION_DATA__METADATA = ProviderPackage.SERVICE__METADATA;

	/**
	 * The feature id for the '<em><b>Service Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_CURRENT_CONNECTION_DATA__SERVICE_NAME = ProviderPackage.SERVICE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Service Operation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_CURRENT_CONNECTION_DATA__SERVICE_OPERATION = ProviderPackage.SERVICE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Timestamp</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_CURRENT_CONNECTION_DATA__TIMESTAMP = ProviderPackage.SERVICE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Customer Info Current Connection Data</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_CURRENT_CONNECTION_DATA_FEATURE_COUNT = ProviderPackage.SERVICE_FEATURE_COUNT + 3;

	/**
	 * The operation id for the '<em>EIs Set</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_CURRENT_CONNECTION_DATA___EIS_SET__ESTRUCTURALFEATURE = ProviderPackage.SERVICE___EIS_SET__ESTRUCTURALFEATURE;

	/**
	 * The number of operations of the '<em>Customer Info Current Connection Data</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_CURRENT_CONNECTION_DATA_OPERATION_COUNT = ProviderPackage.SERVICE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.jena.model.sensinact.ibis.impl.CustomerInfoCurrentDisplayContentDataImpl <em>Customer Info Current Display Content Data</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.jena.model.sensinact.ibis.impl.CustomerInfoCurrentDisplayContentDataImpl
	 * @see de.jena.model.sensinact.ibis.impl.IbisSensinactPackageImpl#getCustomerInfoCurrentDisplayContentData()
	 * @generated
	 */
	int CUSTOMER_INFO_CURRENT_DISPLAY_CONTENT_DATA = 8;

	/**
	 * The feature id for the '<em><b>Metadata</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_CURRENT_DISPLAY_CONTENT_DATA__METADATA = ProviderPackage.SERVICE__METADATA;

	/**
	 * The feature id for the '<em><b>Service Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_CURRENT_DISPLAY_CONTENT_DATA__SERVICE_NAME = ProviderPackage.SERVICE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Service Operation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_CURRENT_DISPLAY_CONTENT_DATA__SERVICE_OPERATION = ProviderPackage.SERVICE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Timestamp</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_CURRENT_DISPLAY_CONTENT_DATA__TIMESTAMP = ProviderPackage.SERVICE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Customer Info Current Display Content Data</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_CURRENT_DISPLAY_CONTENT_DATA_FEATURE_COUNT = ProviderPackage.SERVICE_FEATURE_COUNT + 3;

	/**
	 * The operation id for the '<em>EIs Set</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_CURRENT_DISPLAY_CONTENT_DATA___EIS_SET__ESTRUCTURALFEATURE = ProviderPackage.SERVICE___EIS_SET__ESTRUCTURALFEATURE;

	/**
	 * The number of operations of the '<em>Customer Info Current Display Content Data</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_INFO_CURRENT_DISPLAY_CONTENT_DATA_OPERATION_COUNT = ProviderPackage.SERVICE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.jena.model.sensinact.ibis.impl.IbisAdminImpl <em>Ibis Admin</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.jena.model.sensinact.ibis.impl.IbisAdminImpl
	 * @see de.jena.model.sensinact.ibis.impl.IbisSensinactPackageImpl#getIbisAdmin()
	 * @generated
	 */
	int IBIS_ADMIN = 9;

	/**
	 * The feature id for the '<em><b>Metadata</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IBIS_ADMIN__METADATA = ProviderPackage.SERVICE__METADATA;

	/**
	 * The feature id for the '<em><b>Device Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IBIS_ADMIN__DEVICE_TYPE = ProviderPackage.SERVICE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Ibis Admin</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IBIS_ADMIN_FEATURE_COUNT = ProviderPackage.SERVICE_FEATURE_COUNT + 1;

	/**
	 * The operation id for the '<em>EIs Set</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IBIS_ADMIN___EIS_SET__ESTRUCTURALFEATURE = ProviderPackage.SERVICE___EIS_SET__ESTRUCTURALFEATURE;

	/**
	 * The number of operations of the '<em>Ibis Admin</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IBIS_ADMIN_OPERATION_COUNT = ProviderPackage.SERVICE_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link de.jena.model.sensinact.ibis.IbisDevice <em>Ibis Device</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ibis Device</em>'.
	 * @see de.jena.model.sensinact.ibis.IbisDevice
	 * @generated
	 */
	EClass getIbisDevice();

	/**
	 * Returns the meta object for the containment reference '{@link de.jena.model.sensinact.ibis.IbisDevice#getCustomerInfoAllData <em>Customer Info All Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Customer Info All Data</em>'.
	 * @see de.jena.model.sensinact.ibis.IbisDevice#getCustomerInfoAllData()
	 * @see #getIbisDevice()
	 * @generated
	 */
	EReference getIbisDevice_CustomerInfoAllData();

	/**
	 * Returns the meta object for the containment reference '{@link de.jena.model.sensinact.ibis.IbisDevice#getCustomerInfoCurrentStopIndexData <em>Customer Info Current Stop Index Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Customer Info Current Stop Index Data</em>'.
	 * @see de.jena.model.sensinact.ibis.IbisDevice#getCustomerInfoCurrentStopIndexData()
	 * @see #getIbisDevice()
	 * @generated
	 */
	EReference getIbisDevice_CustomerInfoCurrentStopIndexData();

	/**
	 * Returns the meta object for the containment reference '{@link de.jena.model.sensinact.ibis.IbisDevice#getCustomerInfoCurrentStopPointData <em>Customer Info Current Stop Point Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Customer Info Current Stop Point Data</em>'.
	 * @see de.jena.model.sensinact.ibis.IbisDevice#getCustomerInfoCurrentStopPointData()
	 * @see #getIbisDevice()
	 * @generated
	 */
	EReference getIbisDevice_CustomerInfoCurrentStopPointData();

	/**
	 * Returns the meta object for the containment reference '{@link de.jena.model.sensinact.ibis.IbisDevice#getCustomerInfoTripData <em>Customer Info Trip Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Customer Info Trip Data</em>'.
	 * @see de.jena.model.sensinact.ibis.IbisDevice#getCustomerInfoTripData()
	 * @see #getIbisDevice()
	 * @generated
	 */
	EReference getIbisDevice_CustomerInfoTripData();

	/**
	 * Returns the meta object for the containment reference '{@link de.jena.model.sensinact.ibis.IbisDevice#getCustomerInfoVehicleData <em>Customer Info Vehicle Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Customer Info Vehicle Data</em>'.
	 * @see de.jena.model.sensinact.ibis.IbisDevice#getCustomerInfoVehicleData()
	 * @see #getIbisDevice()
	 * @generated
	 */
	EReference getIbisDevice_CustomerInfoVehicleData();

	/**
	 * Returns the meta object for the containment reference '{@link de.jena.model.sensinact.ibis.IbisDevice#getCustomerInfoCurrentAnnouncementData <em>Customer Info Current Announcement Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Customer Info Current Announcement Data</em>'.
	 * @see de.jena.model.sensinact.ibis.IbisDevice#getCustomerInfoCurrentAnnouncementData()
	 * @see #getIbisDevice()
	 * @generated
	 */
	EReference getIbisDevice_CustomerInfoCurrentAnnouncementData();

	/**
	 * Returns the meta object for the containment reference '{@link de.jena.model.sensinact.ibis.IbisDevice#getCustomerInfoCurrentConnectionData <em>Customer Info Current Connection Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Customer Info Current Connection Data</em>'.
	 * @see de.jena.model.sensinact.ibis.IbisDevice#getCustomerInfoCurrentConnectionData()
	 * @see #getIbisDevice()
	 * @generated
	 */
	EReference getIbisDevice_CustomerInfoCurrentConnectionData();

	/**
	 * Returns the meta object for the containment reference '{@link de.jena.model.sensinact.ibis.IbisDevice#getCustomerInfoCurrentDisplayContentData <em>Customer Info Current Display Content Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Customer Info Current Display Content Data</em>'.
	 * @see de.jena.model.sensinact.ibis.IbisDevice#getCustomerInfoCurrentDisplayContentData()
	 * @see #getIbisDevice()
	 * @generated
	 */
	EReference getIbisDevice_CustomerInfoCurrentDisplayContentData();

	/**
	 * Returns the meta object for the containment reference '{@link de.jena.model.sensinact.ibis.IbisDevice#getIbisAdmin <em>Ibis Admin</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Ibis Admin</em>'.
	 * @see de.jena.model.sensinact.ibis.IbisDevice#getIbisAdmin()
	 * @see #getIbisDevice()
	 * @generated
	 */
	EReference getIbisDevice_IbisAdmin();

	/**
	 * Returns the meta object for class '{@link de.jena.model.sensinact.ibis.CustomerInfoAllData <em>Customer Info All Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Customer Info All Data</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoAllData
	 * @generated
	 */
	EClass getCustomerInfoAllData();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoAllData#getServiceName <em>Service Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Service Name</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoAllData#getServiceName()
	 * @see #getCustomerInfoAllData()
	 * @generated
	 */
	EAttribute getCustomerInfoAllData_ServiceName();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoAllData#getServiceOperation <em>Service Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Service Operation</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoAllData#getServiceOperation()
	 * @see #getCustomerInfoAllData()
	 * @generated
	 */
	EAttribute getCustomerInfoAllData_ServiceOperation();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoAllData#getTimestamp <em>Timestamp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Timestamp</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoAllData#getTimestamp()
	 * @see #getCustomerInfoAllData()
	 * @generated
	 */
	EAttribute getCustomerInfoAllData_Timestamp();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoAllData#getVehicleRef <em>Vehicle Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Vehicle Ref</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoAllData#getVehicleRef()
	 * @see #getCustomerInfoAllData()
	 * @generated
	 */
	EAttribute getCustomerInfoAllData_VehicleRef();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoAllData#getDefaultLanguage <em>Default Language</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Default Language</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoAllData#getDefaultLanguage()
	 * @see #getCustomerInfoAllData()
	 * @generated
	 */
	EAttribute getCustomerInfoAllData_DefaultLanguage();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoAllData#getCurrentStopIndex <em>Current Stop Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Current Stop Index</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoAllData#getCurrentStopIndex()
	 * @see #getCustomerInfoAllData()
	 * @generated
	 */
	EAttribute getCustomerInfoAllData_CurrentStopIndex();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoAllData#getRouteDeviation <em>Route Deviation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Route Deviation</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoAllData#getRouteDeviation()
	 * @see #getCustomerInfoAllData()
	 * @generated
	 */
	EAttribute getCustomerInfoAllData_RouteDeviation();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoAllData#getDoorState <em>Door State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Door State</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoAllData#getDoorState()
	 * @see #getCustomerInfoAllData()
	 * @generated
	 */
	EAttribute getCustomerInfoAllData_DoorState();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoAllData#isInPanic <em>In Panic</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>In Panic</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoAllData#isInPanic()
	 * @see #getCustomerInfoAllData()
	 * @generated
	 */
	EAttribute getCustomerInfoAllData_InPanic();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoAllData#isVehicleStopRequested <em>Vehicle Stop Requested</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Vehicle Stop Requested</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoAllData#isVehicleStopRequested()
	 * @see #getCustomerInfoAllData()
	 * @generated
	 */
	EAttribute getCustomerInfoAllData_VehicleStopRequested();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoAllData#getExitSide <em>Exit Side</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Exit Side</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoAllData#getExitSide()
	 * @see #getCustomerInfoAllData()
	 * @generated
	 */
	EAttribute getCustomerInfoAllData_ExitSide();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoAllData#isMovingDirectionForward <em>Moving Direction Forward</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Moving Direction Forward</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoAllData#isMovingDirectionForward()
	 * @see #getCustomerInfoAllData()
	 * @generated
	 */
	EAttribute getCustomerInfoAllData_MovingDirectionForward();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoAllData#getVehicleMode <em>Vehicle Mode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Vehicle Mode</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoAllData#getVehicleMode()
	 * @see #getCustomerInfoAllData()
	 * @generated
	 */
	EAttribute getCustomerInfoAllData_VehicleMode();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoAllData#isSpeakerActive <em>Speaker Active</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Speaker Active</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoAllData#isSpeakerActive()
	 * @see #getCustomerInfoAllData()
	 * @generated
	 */
	EAttribute getCustomerInfoAllData_SpeakerActive();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoAllData#isStopInformationActive <em>Stop Information Active</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Stop Information Active</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoAllData#isStopInformationActive()
	 * @see #getCustomerInfoAllData()
	 * @generated
	 */
	EAttribute getCustomerInfoAllData_StopInformationActive();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoAllData#getTripState <em>Trip State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Trip State</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoAllData#getTripState()
	 * @see #getCustomerInfoAllData()
	 * @generated
	 */
	EAttribute getCustomerInfoAllData_TripState();

	/**
	 * Returns the meta object for class '{@link de.jena.model.sensinact.ibis.CustomerInfoCurrentStopIndexData <em>Customer Info Current Stop Index Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Customer Info Current Stop Index Data</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoCurrentStopIndexData
	 * @generated
	 */
	EClass getCustomerInfoCurrentStopIndexData();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoCurrentStopIndexData#getServiceName <em>Service Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Service Name</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoCurrentStopIndexData#getServiceName()
	 * @see #getCustomerInfoCurrentStopIndexData()
	 * @generated
	 */
	EAttribute getCustomerInfoCurrentStopIndexData_ServiceName();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoCurrentStopIndexData#getServiceOperation <em>Service Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Service Operation</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoCurrentStopIndexData#getServiceOperation()
	 * @see #getCustomerInfoCurrentStopIndexData()
	 * @generated
	 */
	EAttribute getCustomerInfoCurrentStopIndexData_ServiceOperation();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoCurrentStopIndexData#getTimestamp <em>Timestamp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Timestamp</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoCurrentStopIndexData#getTimestamp()
	 * @see #getCustomerInfoCurrentStopIndexData()
	 * @generated
	 */
	EAttribute getCustomerInfoCurrentStopIndexData_Timestamp();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoCurrentStopIndexData#getCurrentStopIndex <em>Current Stop Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Current Stop Index</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoCurrentStopIndexData#getCurrentStopIndex()
	 * @see #getCustomerInfoCurrentStopIndexData()
	 * @generated
	 */
	EAttribute getCustomerInfoCurrentStopIndexData_CurrentStopIndex();

	/**
	 * Returns the meta object for class '{@link de.jena.model.sensinact.ibis.CustomerInfoCurrentStopPointData <em>Customer Info Current Stop Point Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Customer Info Current Stop Point Data</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoCurrentStopPointData
	 * @generated
	 */
	EClass getCustomerInfoCurrentStopPointData();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoCurrentStopPointData#getServiceName <em>Service Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Service Name</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoCurrentStopPointData#getServiceName()
	 * @see #getCustomerInfoCurrentStopPointData()
	 * @generated
	 */
	EAttribute getCustomerInfoCurrentStopPointData_ServiceName();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoCurrentStopPointData#getServiceOperation <em>Service Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Service Operation</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoCurrentStopPointData#getServiceOperation()
	 * @see #getCustomerInfoCurrentStopPointData()
	 * @generated
	 */
	EAttribute getCustomerInfoCurrentStopPointData_ServiceOperation();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoCurrentStopPointData#getTimestamp <em>Timestamp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Timestamp</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoCurrentStopPointData#getTimestamp()
	 * @see #getCustomerInfoCurrentStopPointData()
	 * @generated
	 */
	EAttribute getCustomerInfoCurrentStopPointData_Timestamp();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoCurrentStopPointData#getStopIndex <em>Stop Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Stop Index</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoCurrentStopPointData#getStopIndex()
	 * @see #getCustomerInfoCurrentStopPointData()
	 * @generated
	 */
	EAttribute getCustomerInfoCurrentStopPointData_StopIndex();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoCurrentStopPointData#getStopRef <em>Stop Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Stop Ref</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoCurrentStopPointData#getStopRef()
	 * @see #getCustomerInfoCurrentStopPointData()
	 * @generated
	 */
	EAttribute getCustomerInfoCurrentStopPointData_StopRef();

	/**
	 * Returns the meta object for the attribute list '{@link de.jena.model.sensinact.ibis.CustomerInfoCurrentStopPointData#getStopName <em>Stop Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Stop Name</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoCurrentStopPointData#getStopName()
	 * @see #getCustomerInfoCurrentStopPointData()
	 * @generated
	 */
	EAttribute getCustomerInfoCurrentStopPointData_StopName();

	/**
	 * Returns the meta object for the attribute list '{@link de.jena.model.sensinact.ibis.CustomerInfoCurrentStopPointData#getStopAlternativeName <em>Stop Alternative Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Stop Alternative Name</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoCurrentStopPointData#getStopAlternativeName()
	 * @see #getCustomerInfoCurrentStopPointData()
	 * @generated
	 */
	EAttribute getCustomerInfoCurrentStopPointData_StopAlternativeName();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoCurrentStopPointData#getPlatform <em>Platform</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Platform</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoCurrentStopPointData#getPlatform()
	 * @see #getCustomerInfoCurrentStopPointData()
	 * @generated
	 */
	EAttribute getCustomerInfoCurrentStopPointData_Platform();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoCurrentStopPointData#getArrivalScheduled <em>Arrival Scheduled</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Arrival Scheduled</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoCurrentStopPointData#getArrivalScheduled()
	 * @see #getCustomerInfoCurrentStopPointData()
	 * @generated
	 */
	EAttribute getCustomerInfoCurrentStopPointData_ArrivalScheduled();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoCurrentStopPointData#getArrivalExpected <em>Arrival Expected</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Arrival Expected</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoCurrentStopPointData#getArrivalExpected()
	 * @see #getCustomerInfoCurrentStopPointData()
	 * @generated
	 */
	EAttribute getCustomerInfoCurrentStopPointData_ArrivalExpected();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoCurrentStopPointData#getDepartureScheduled <em>Departure Scheduled</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Departure Scheduled</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoCurrentStopPointData#getDepartureScheduled()
	 * @see #getCustomerInfoCurrentStopPointData()
	 * @generated
	 */
	EAttribute getCustomerInfoCurrentStopPointData_DepartureScheduled();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoCurrentStopPointData#getDepartureExpected <em>Departure Expected</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Departure Expected</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoCurrentStopPointData#getDepartureExpected()
	 * @see #getCustomerInfoCurrentStopPointData()
	 * @generated
	 */
	EAttribute getCustomerInfoCurrentStopPointData_DepartureExpected();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoCurrentStopPointData#getRecordedArrivalTime <em>Recorded Arrival Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Recorded Arrival Time</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoCurrentStopPointData#getRecordedArrivalTime()
	 * @see #getCustomerInfoCurrentStopPointData()
	 * @generated
	 */
	EAttribute getCustomerInfoCurrentStopPointData_RecordedArrivalTime();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoCurrentStopPointData#getDistanceToNextStop <em>Distance To Next Stop</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Distance To Next Stop</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoCurrentStopPointData#getDistanceToNextStop()
	 * @see #getCustomerInfoCurrentStopPointData()
	 * @generated
	 */
	EAttribute getCustomerInfoCurrentStopPointData_DistanceToNextStop();

	/**
	 * Returns the meta object for the attribute list '{@link de.jena.model.sensinact.ibis.CustomerInfoCurrentStopPointData#getFareZone <em>Fare Zone</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Fare Zone</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoCurrentStopPointData#getFareZone()
	 * @see #getCustomerInfoCurrentStopPointData()
	 * @generated
	 */
	EAttribute getCustomerInfoCurrentStopPointData_FareZone();

	/**
	 * Returns the meta object for class '{@link de.jena.model.sensinact.ibis.CustomerInfoTripData <em>Customer Info Trip Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Customer Info Trip Data</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoTripData
	 * @generated
	 */
	EClass getCustomerInfoTripData();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoTripData#getServiceName <em>Service Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Service Name</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoTripData#getServiceName()
	 * @see #getCustomerInfoTripData()
	 * @generated
	 */
	EAttribute getCustomerInfoTripData_ServiceName();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoTripData#getServiceOperation <em>Service Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Service Operation</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoTripData#getServiceOperation()
	 * @see #getCustomerInfoTripData()
	 * @generated
	 */
	EAttribute getCustomerInfoTripData_ServiceOperation();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoTripData#getTimestamp <em>Timestamp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Timestamp</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoTripData#getTimestamp()
	 * @see #getCustomerInfoTripData()
	 * @generated
	 */
	EAttribute getCustomerInfoTripData_Timestamp();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoTripData#getVehicleRef <em>Vehicle Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Vehicle Ref</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoTripData#getVehicleRef()
	 * @see #getCustomerInfoTripData()
	 * @generated
	 */
	EAttribute getCustomerInfoTripData_VehicleRef();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoTripData#getDefaultLanguage <em>Default Language</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Default Language</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoTripData#getDefaultLanguage()
	 * @see #getCustomerInfoTripData()
	 * @generated
	 */
	EAttribute getCustomerInfoTripData_DefaultLanguage();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoTripData#getCurrentStopIndex <em>Current Stop Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Current Stop Index</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoTripData#getCurrentStopIndex()
	 * @see #getCustomerInfoTripData()
	 * @generated
	 */
	EAttribute getCustomerInfoTripData_CurrentStopIndex();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoTripData#getTripRef <em>Trip Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Trip Ref</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoTripData#getTripRef()
	 * @see #getCustomerInfoTripData()
	 * @generated
	 */
	EAttribute getCustomerInfoTripData_TripRef();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoTripData#getLocationState <em>Location State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Location State</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoTripData#getLocationState()
	 * @see #getCustomerInfoTripData()
	 * @generated
	 */
	EAttribute getCustomerInfoTripData_LocationState();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoTripData#getTimetableDelay <em>Timetable Delay</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Timetable Delay</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoTripData#getTimetableDelay()
	 * @see #getCustomerInfoTripData()
	 * @generated
	 */
	EAttribute getCustomerInfoTripData_TimetableDelay();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoTripData#getRouteDirection <em>Route Direction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Route Direction</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoTripData#getRouteDirection()
	 * @see #getCustomerInfoTripData()
	 * @generated
	 */
	EAttribute getCustomerInfoTripData_RouteDirection();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoTripData#getRunNumber <em>Run Number</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Run Number</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoTripData#getRunNumber()
	 * @see #getCustomerInfoTripData()
	 * @generated
	 */
	EAttribute getCustomerInfoTripData_RunNumber();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoTripData#getPatternNumber <em>Pattern Number</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pattern Number</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoTripData#getPatternNumber()
	 * @see #getCustomerInfoTripData()
	 * @generated
	 */
	EAttribute getCustomerInfoTripData_PatternNumber();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoTripData#getPathDestinationNumber <em>Path Destination Number</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Path Destination Number</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoTripData#getPathDestinationNumber()
	 * @see #getCustomerInfoTripData()
	 * @generated
	 */
	EAttribute getCustomerInfoTripData_PathDestinationNumber();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoTripData#getAdditionalTextMsg <em>Additional Text Msg</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Additional Text Msg</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoTripData#getAdditionalTextMsg()
	 * @see #getCustomerInfoTripData()
	 * @generated
	 */
	EAttribute getCustomerInfoTripData_AdditionalTextMsg();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoTripData#getAdditionalTextMsg1 <em>Additional Text Msg1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Additional Text Msg1</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoTripData#getAdditionalTextMsg1()
	 * @see #getCustomerInfoTripData()
	 * @generated
	 */
	EAttribute getCustomerInfoTripData_AdditionalTextMsg1();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoTripData#getAdditionalTextMsg2 <em>Additional Text Msg2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Additional Text Msg2</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoTripData#getAdditionalTextMsg2()
	 * @see #getCustomerInfoTripData()
	 * @generated
	 */
	EAttribute getCustomerInfoTripData_AdditionalTextMsg2();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoTripData#getAdditionalTextMsg3 <em>Additional Text Msg3</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Additional Text Msg3</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoTripData#getAdditionalTextMsg3()
	 * @see #getCustomerInfoTripData()
	 * @generated
	 */
	EAttribute getCustomerInfoTripData_AdditionalTextMsg3();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoTripData#getAdditionalTextMsg4 <em>Additional Text Msg4</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Additional Text Msg4</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoTripData#getAdditionalTextMsg4()
	 * @see #getCustomerInfoTripData()
	 * @generated
	 */
	EAttribute getCustomerInfoTripData_AdditionalTextMsg4();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoTripData#getAdditionalTextMsg5 <em>Additional Text Msg5</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Additional Text Msg5</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoTripData#getAdditionalTextMsg5()
	 * @see #getCustomerInfoTripData()
	 * @generated
	 */
	EAttribute getCustomerInfoTripData_AdditionalTextMsg5();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoTripData#getAdditionalTextMsg6 <em>Additional Text Msg6</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Additional Text Msg6</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoTripData#getAdditionalTextMsg6()
	 * @see #getCustomerInfoTripData()
	 * @generated
	 */
	EAttribute getCustomerInfoTripData_AdditionalTextMsg6();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoTripData#getAdditionalTextMsg7 <em>Additional Text Msg7</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Additional Text Msg7</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoTripData#getAdditionalTextMsg7()
	 * @see #getCustomerInfoTripData()
	 * @generated
	 */
	EAttribute getCustomerInfoTripData_AdditionalTextMsg7();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoTripData#getAdditionalTextMsg8 <em>Additional Text Msg8</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Additional Text Msg8</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoTripData#getAdditionalTextMsg8()
	 * @see #getCustomerInfoTripData()
	 * @generated
	 */
	EAttribute getCustomerInfoTripData_AdditionalTextMsg8();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoTripData#getAdditionalTextMsg9 <em>Additional Text Msg9</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Additional Text Msg9</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoTripData#getAdditionalTextMsg9()
	 * @see #getCustomerInfoTripData()
	 * @generated
	 */
	EAttribute getCustomerInfoTripData_AdditionalTextMsg9();

	/**
	 * Returns the meta object for class '{@link de.jena.model.sensinact.ibis.CustomerInfoVehicleData <em>Customer Info Vehicle Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Customer Info Vehicle Data</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoVehicleData
	 * @generated
	 */
	EClass getCustomerInfoVehicleData();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoVehicleData#getServiceName <em>Service Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Service Name</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoVehicleData#getServiceName()
	 * @see #getCustomerInfoVehicleData()
	 * @generated
	 */
	EAttribute getCustomerInfoVehicleData_ServiceName();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoVehicleData#getServiceOperation <em>Service Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Service Operation</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoVehicleData#getServiceOperation()
	 * @see #getCustomerInfoVehicleData()
	 * @generated
	 */
	EAttribute getCustomerInfoVehicleData_ServiceOperation();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoVehicleData#getTimestamp <em>Timestamp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Timestamp</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoVehicleData#getTimestamp()
	 * @see #getCustomerInfoVehicleData()
	 * @generated
	 */
	EAttribute getCustomerInfoVehicleData_Timestamp();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoVehicleData#getVehicleRef <em>Vehicle Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Vehicle Ref</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoVehicleData#getVehicleRef()
	 * @see #getCustomerInfoVehicleData()
	 * @generated
	 */
	EAttribute getCustomerInfoVehicleData_VehicleRef();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoVehicleData#getRouteDeviation <em>Route Deviation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Route Deviation</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoVehicleData#getRouteDeviation()
	 * @see #getCustomerInfoVehicleData()
	 * @generated
	 */
	EAttribute getCustomerInfoVehicleData_RouteDeviation();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoVehicleData#getDoorState <em>Door State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Door State</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoVehicleData#getDoorState()
	 * @see #getCustomerInfoVehicleData()
	 * @generated
	 */
	EAttribute getCustomerInfoVehicleData_DoorState();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoVehicleData#isInPanic <em>In Panic</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>In Panic</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoVehicleData#isInPanic()
	 * @see #getCustomerInfoVehicleData()
	 * @generated
	 */
	EAttribute getCustomerInfoVehicleData_InPanic();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoVehicleData#isVehicleStopRequested <em>Vehicle Stop Requested</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Vehicle Stop Requested</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoVehicleData#isVehicleStopRequested()
	 * @see #getCustomerInfoVehicleData()
	 * @generated
	 */
	EAttribute getCustomerInfoVehicleData_VehicleStopRequested();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoVehicleData#getExitSide <em>Exit Side</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Exit Side</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoVehicleData#getExitSide()
	 * @see #getCustomerInfoVehicleData()
	 * @generated
	 */
	EAttribute getCustomerInfoVehicleData_ExitSide();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoVehicleData#isMovingDirectionForward <em>Moving Direction Forward</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Moving Direction Forward</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoVehicleData#isMovingDirectionForward()
	 * @see #getCustomerInfoVehicleData()
	 * @generated
	 */
	EAttribute getCustomerInfoVehicleData_MovingDirectionForward();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoVehicleData#getVehicleMode <em>Vehicle Mode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Vehicle Mode</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoVehicleData#getVehicleMode()
	 * @see #getCustomerInfoVehicleData()
	 * @generated
	 */
	EAttribute getCustomerInfoVehicleData_VehicleMode();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoVehicleData#isSpeakerActive <em>Speaker Active</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Speaker Active</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoVehicleData#isSpeakerActive()
	 * @see #getCustomerInfoVehicleData()
	 * @generated
	 */
	EAttribute getCustomerInfoVehicleData_SpeakerActive();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoVehicleData#isStopInformationActive <em>Stop Information Active</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Stop Information Active</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoVehicleData#isStopInformationActive()
	 * @see #getCustomerInfoVehicleData()
	 * @generated
	 */
	EAttribute getCustomerInfoVehicleData_StopInformationActive();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoVehicleData#getTripState <em>Trip State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Trip State</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoVehicleData#getTripState()
	 * @see #getCustomerInfoVehicleData()
	 * @generated
	 */
	EAttribute getCustomerInfoVehicleData_TripState();

	/**
	 * Returns the meta object for class '{@link de.jena.model.sensinact.ibis.CustomerInfoCurrentAnnouncementData <em>Customer Info Current Announcement Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Customer Info Current Announcement Data</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoCurrentAnnouncementData
	 * @generated
	 */
	EClass getCustomerInfoCurrentAnnouncementData();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoCurrentAnnouncementData#getServiceName <em>Service Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Service Name</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoCurrentAnnouncementData#getServiceName()
	 * @see #getCustomerInfoCurrentAnnouncementData()
	 * @generated
	 */
	EAttribute getCustomerInfoCurrentAnnouncementData_ServiceName();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoCurrentAnnouncementData#getServiceOperation <em>Service Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Service Operation</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoCurrentAnnouncementData#getServiceOperation()
	 * @see #getCustomerInfoCurrentAnnouncementData()
	 * @generated
	 */
	EAttribute getCustomerInfoCurrentAnnouncementData_ServiceOperation();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoCurrentAnnouncementData#getTimestamp <em>Timestamp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Timestamp</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoCurrentAnnouncementData#getTimestamp()
	 * @see #getCustomerInfoCurrentAnnouncementData()
	 * @generated
	 */
	EAttribute getCustomerInfoCurrentAnnouncementData_Timestamp();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoCurrentAnnouncementData#getAnnouncementRef <em>Announcement Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Announcement Ref</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoCurrentAnnouncementData#getAnnouncementRef()
	 * @see #getCustomerInfoCurrentAnnouncementData()
	 * @generated
	 */
	EAttribute getCustomerInfoCurrentAnnouncementData_AnnouncementRef();

	/**
	 * Returns the meta object for the attribute list '{@link de.jena.model.sensinact.ibis.CustomerInfoCurrentAnnouncementData#getAnnouncementText <em>Announcement Text</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Announcement Text</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoCurrentAnnouncementData#getAnnouncementText()
	 * @see #getCustomerInfoCurrentAnnouncementData()
	 * @generated
	 */
	EAttribute getCustomerInfoCurrentAnnouncementData_AnnouncementText();

	/**
	 * Returns the meta object for the attribute list '{@link de.jena.model.sensinact.ibis.CustomerInfoCurrentAnnouncementData#getAnnouncementTTSText <em>Announcement TTS Text</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Announcement TTS Text</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoCurrentAnnouncementData#getAnnouncementTTSText()
	 * @see #getCustomerInfoCurrentAnnouncementData()
	 * @generated
	 */
	EAttribute getCustomerInfoCurrentAnnouncementData_AnnouncementTTSText();

	/**
	 * Returns the meta object for class '{@link de.jena.model.sensinact.ibis.CustomerInfoCurrentConnectionData <em>Customer Info Current Connection Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Customer Info Current Connection Data</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoCurrentConnectionData
	 * @generated
	 */
	EClass getCustomerInfoCurrentConnectionData();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoCurrentConnectionData#getServiceName <em>Service Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Service Name</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoCurrentConnectionData#getServiceName()
	 * @see #getCustomerInfoCurrentConnectionData()
	 * @generated
	 */
	EAttribute getCustomerInfoCurrentConnectionData_ServiceName();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoCurrentConnectionData#getServiceOperation <em>Service Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Service Operation</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoCurrentConnectionData#getServiceOperation()
	 * @see #getCustomerInfoCurrentConnectionData()
	 * @generated
	 */
	EAttribute getCustomerInfoCurrentConnectionData_ServiceOperation();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoCurrentConnectionData#getTimestamp <em>Timestamp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Timestamp</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoCurrentConnectionData#getTimestamp()
	 * @see #getCustomerInfoCurrentConnectionData()
	 * @generated
	 */
	EAttribute getCustomerInfoCurrentConnectionData_Timestamp();

	/**
	 * Returns the meta object for class '{@link de.jena.model.sensinact.ibis.CustomerInfoCurrentDisplayContentData <em>Customer Info Current Display Content Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Customer Info Current Display Content Data</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoCurrentDisplayContentData
	 * @generated
	 */
	EClass getCustomerInfoCurrentDisplayContentData();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoCurrentDisplayContentData#getServiceName <em>Service Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Service Name</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoCurrentDisplayContentData#getServiceName()
	 * @see #getCustomerInfoCurrentDisplayContentData()
	 * @generated
	 */
	EAttribute getCustomerInfoCurrentDisplayContentData_ServiceName();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoCurrentDisplayContentData#getServiceOperation <em>Service Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Service Operation</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoCurrentDisplayContentData#getServiceOperation()
	 * @see #getCustomerInfoCurrentDisplayContentData()
	 * @generated
	 */
	EAttribute getCustomerInfoCurrentDisplayContentData_ServiceOperation();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.CustomerInfoCurrentDisplayContentData#getTimestamp <em>Timestamp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Timestamp</em>'.
	 * @see de.jena.model.sensinact.ibis.CustomerInfoCurrentDisplayContentData#getTimestamp()
	 * @see #getCustomerInfoCurrentDisplayContentData()
	 * @generated
	 */
	EAttribute getCustomerInfoCurrentDisplayContentData_Timestamp();

	/**
	 * Returns the meta object for class '{@link de.jena.model.sensinact.ibis.IbisAdmin <em>Ibis Admin</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ibis Admin</em>'.
	 * @see de.jena.model.sensinact.ibis.IbisAdmin
	 * @generated
	 */
	EClass getIbisAdmin();

	/**
	 * Returns the meta object for the attribute '{@link de.jena.model.sensinact.ibis.IbisAdmin#getDeviceType <em>Device Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Device Type</em>'.
	 * @see de.jena.model.sensinact.ibis.IbisAdmin#getDeviceType()
	 * @see #getIbisAdmin()
	 * @generated
	 */
	EAttribute getIbisAdmin_DeviceType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	IbisSensinactFactory getIbisSensinactFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link de.jena.model.sensinact.ibis.impl.IbisDeviceImpl <em>Ibis Device</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.jena.model.sensinact.ibis.impl.IbisDeviceImpl
		 * @see de.jena.model.sensinact.ibis.impl.IbisSensinactPackageImpl#getIbisDevice()
		 * @generated
		 */
		EClass IBIS_DEVICE = eINSTANCE.getIbisDevice();

		/**
		 * The meta object literal for the '<em><b>Customer Info All Data</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IBIS_DEVICE__CUSTOMER_INFO_ALL_DATA = eINSTANCE.getIbisDevice_CustomerInfoAllData();

		/**
		 * The meta object literal for the '<em><b>Customer Info Current Stop Index Data</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IBIS_DEVICE__CUSTOMER_INFO_CURRENT_STOP_INDEX_DATA = eINSTANCE.getIbisDevice_CustomerInfoCurrentStopIndexData();

		/**
		 * The meta object literal for the '<em><b>Customer Info Current Stop Point Data</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IBIS_DEVICE__CUSTOMER_INFO_CURRENT_STOP_POINT_DATA = eINSTANCE.getIbisDevice_CustomerInfoCurrentStopPointData();

		/**
		 * The meta object literal for the '<em><b>Customer Info Trip Data</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IBIS_DEVICE__CUSTOMER_INFO_TRIP_DATA = eINSTANCE.getIbisDevice_CustomerInfoTripData();

		/**
		 * The meta object literal for the '<em><b>Customer Info Vehicle Data</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IBIS_DEVICE__CUSTOMER_INFO_VEHICLE_DATA = eINSTANCE.getIbisDevice_CustomerInfoVehicleData();

		/**
		 * The meta object literal for the '<em><b>Customer Info Current Announcement Data</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IBIS_DEVICE__CUSTOMER_INFO_CURRENT_ANNOUNCEMENT_DATA = eINSTANCE.getIbisDevice_CustomerInfoCurrentAnnouncementData();

		/**
		 * The meta object literal for the '<em><b>Customer Info Current Connection Data</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IBIS_DEVICE__CUSTOMER_INFO_CURRENT_CONNECTION_DATA = eINSTANCE.getIbisDevice_CustomerInfoCurrentConnectionData();

		/**
		 * The meta object literal for the '<em><b>Customer Info Current Display Content Data</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IBIS_DEVICE__CUSTOMER_INFO_CURRENT_DISPLAY_CONTENT_DATA = eINSTANCE.getIbisDevice_CustomerInfoCurrentDisplayContentData();

		/**
		 * The meta object literal for the '<em><b>Ibis Admin</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IBIS_DEVICE__IBIS_ADMIN = eINSTANCE.getIbisDevice_IbisAdmin();

		/**
		 * The meta object literal for the '{@link de.jena.model.sensinact.ibis.impl.CustomerInfoAllDataImpl <em>Customer Info All Data</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.jena.model.sensinact.ibis.impl.CustomerInfoAllDataImpl
		 * @see de.jena.model.sensinact.ibis.impl.IbisSensinactPackageImpl#getCustomerInfoAllData()
		 * @generated
		 */
		EClass CUSTOMER_INFO_ALL_DATA = eINSTANCE.getCustomerInfoAllData();

		/**
		 * The meta object literal for the '<em><b>Service Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_ALL_DATA__SERVICE_NAME = eINSTANCE.getCustomerInfoAllData_ServiceName();

		/**
		 * The meta object literal for the '<em><b>Service Operation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_ALL_DATA__SERVICE_OPERATION = eINSTANCE.getCustomerInfoAllData_ServiceOperation();

		/**
		 * The meta object literal for the '<em><b>Timestamp</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_ALL_DATA__TIMESTAMP = eINSTANCE.getCustomerInfoAllData_Timestamp();

		/**
		 * The meta object literal for the '<em><b>Vehicle Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_ALL_DATA__VEHICLE_REF = eINSTANCE.getCustomerInfoAllData_VehicleRef();

		/**
		 * The meta object literal for the '<em><b>Default Language</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_ALL_DATA__DEFAULT_LANGUAGE = eINSTANCE.getCustomerInfoAllData_DefaultLanguage();

		/**
		 * The meta object literal for the '<em><b>Current Stop Index</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_ALL_DATA__CURRENT_STOP_INDEX = eINSTANCE.getCustomerInfoAllData_CurrentStopIndex();

		/**
		 * The meta object literal for the '<em><b>Route Deviation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_ALL_DATA__ROUTE_DEVIATION = eINSTANCE.getCustomerInfoAllData_RouteDeviation();

		/**
		 * The meta object literal for the '<em><b>Door State</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_ALL_DATA__DOOR_STATE = eINSTANCE.getCustomerInfoAllData_DoorState();

		/**
		 * The meta object literal for the '<em><b>In Panic</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_ALL_DATA__IN_PANIC = eINSTANCE.getCustomerInfoAllData_InPanic();

		/**
		 * The meta object literal for the '<em><b>Vehicle Stop Requested</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_ALL_DATA__VEHICLE_STOP_REQUESTED = eINSTANCE.getCustomerInfoAllData_VehicleStopRequested();

		/**
		 * The meta object literal for the '<em><b>Exit Side</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_ALL_DATA__EXIT_SIDE = eINSTANCE.getCustomerInfoAllData_ExitSide();

		/**
		 * The meta object literal for the '<em><b>Moving Direction Forward</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_ALL_DATA__MOVING_DIRECTION_FORWARD = eINSTANCE.getCustomerInfoAllData_MovingDirectionForward();

		/**
		 * The meta object literal for the '<em><b>Vehicle Mode</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_ALL_DATA__VEHICLE_MODE = eINSTANCE.getCustomerInfoAllData_VehicleMode();

		/**
		 * The meta object literal for the '<em><b>Speaker Active</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_ALL_DATA__SPEAKER_ACTIVE = eINSTANCE.getCustomerInfoAllData_SpeakerActive();

		/**
		 * The meta object literal for the '<em><b>Stop Information Active</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_ALL_DATA__STOP_INFORMATION_ACTIVE = eINSTANCE.getCustomerInfoAllData_StopInformationActive();

		/**
		 * The meta object literal for the '<em><b>Trip State</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_ALL_DATA__TRIP_STATE = eINSTANCE.getCustomerInfoAllData_TripState();

		/**
		 * The meta object literal for the '{@link de.jena.model.sensinact.ibis.impl.CustomerInfoCurrentStopIndexDataImpl <em>Customer Info Current Stop Index Data</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.jena.model.sensinact.ibis.impl.CustomerInfoCurrentStopIndexDataImpl
		 * @see de.jena.model.sensinact.ibis.impl.IbisSensinactPackageImpl#getCustomerInfoCurrentStopIndexData()
		 * @generated
		 */
		EClass CUSTOMER_INFO_CURRENT_STOP_INDEX_DATA = eINSTANCE.getCustomerInfoCurrentStopIndexData();

		/**
		 * The meta object literal for the '<em><b>Service Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_CURRENT_STOP_INDEX_DATA__SERVICE_NAME = eINSTANCE.getCustomerInfoCurrentStopIndexData_ServiceName();

		/**
		 * The meta object literal for the '<em><b>Service Operation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_CURRENT_STOP_INDEX_DATA__SERVICE_OPERATION = eINSTANCE.getCustomerInfoCurrentStopIndexData_ServiceOperation();

		/**
		 * The meta object literal for the '<em><b>Timestamp</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_CURRENT_STOP_INDEX_DATA__TIMESTAMP = eINSTANCE.getCustomerInfoCurrentStopIndexData_Timestamp();

		/**
		 * The meta object literal for the '<em><b>Current Stop Index</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_CURRENT_STOP_INDEX_DATA__CURRENT_STOP_INDEX = eINSTANCE.getCustomerInfoCurrentStopIndexData_CurrentStopIndex();

		/**
		 * The meta object literal for the '{@link de.jena.model.sensinact.ibis.impl.CustomerInfoCurrentStopPointDataImpl <em>Customer Info Current Stop Point Data</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.jena.model.sensinact.ibis.impl.CustomerInfoCurrentStopPointDataImpl
		 * @see de.jena.model.sensinact.ibis.impl.IbisSensinactPackageImpl#getCustomerInfoCurrentStopPointData()
		 * @generated
		 */
		EClass CUSTOMER_INFO_CURRENT_STOP_POINT_DATA = eINSTANCE.getCustomerInfoCurrentStopPointData();

		/**
		 * The meta object literal for the '<em><b>Service Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_CURRENT_STOP_POINT_DATA__SERVICE_NAME = eINSTANCE.getCustomerInfoCurrentStopPointData_ServiceName();

		/**
		 * The meta object literal for the '<em><b>Service Operation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_CURRENT_STOP_POINT_DATA__SERVICE_OPERATION = eINSTANCE.getCustomerInfoCurrentStopPointData_ServiceOperation();

		/**
		 * The meta object literal for the '<em><b>Timestamp</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_CURRENT_STOP_POINT_DATA__TIMESTAMP = eINSTANCE.getCustomerInfoCurrentStopPointData_Timestamp();

		/**
		 * The meta object literal for the '<em><b>Stop Index</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_CURRENT_STOP_POINT_DATA__STOP_INDEX = eINSTANCE.getCustomerInfoCurrentStopPointData_StopIndex();

		/**
		 * The meta object literal for the '<em><b>Stop Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_CURRENT_STOP_POINT_DATA__STOP_REF = eINSTANCE.getCustomerInfoCurrentStopPointData_StopRef();

		/**
		 * The meta object literal for the '<em><b>Stop Name</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_CURRENT_STOP_POINT_DATA__STOP_NAME = eINSTANCE.getCustomerInfoCurrentStopPointData_StopName();

		/**
		 * The meta object literal for the '<em><b>Stop Alternative Name</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_CURRENT_STOP_POINT_DATA__STOP_ALTERNATIVE_NAME = eINSTANCE.getCustomerInfoCurrentStopPointData_StopAlternativeName();

		/**
		 * The meta object literal for the '<em><b>Platform</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_CURRENT_STOP_POINT_DATA__PLATFORM = eINSTANCE.getCustomerInfoCurrentStopPointData_Platform();

		/**
		 * The meta object literal for the '<em><b>Arrival Scheduled</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_CURRENT_STOP_POINT_DATA__ARRIVAL_SCHEDULED = eINSTANCE.getCustomerInfoCurrentStopPointData_ArrivalScheduled();

		/**
		 * The meta object literal for the '<em><b>Arrival Expected</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_CURRENT_STOP_POINT_DATA__ARRIVAL_EXPECTED = eINSTANCE.getCustomerInfoCurrentStopPointData_ArrivalExpected();

		/**
		 * The meta object literal for the '<em><b>Departure Scheduled</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_CURRENT_STOP_POINT_DATA__DEPARTURE_SCHEDULED = eINSTANCE.getCustomerInfoCurrentStopPointData_DepartureScheduled();

		/**
		 * The meta object literal for the '<em><b>Departure Expected</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_CURRENT_STOP_POINT_DATA__DEPARTURE_EXPECTED = eINSTANCE.getCustomerInfoCurrentStopPointData_DepartureExpected();

		/**
		 * The meta object literal for the '<em><b>Recorded Arrival Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_CURRENT_STOP_POINT_DATA__RECORDED_ARRIVAL_TIME = eINSTANCE.getCustomerInfoCurrentStopPointData_RecordedArrivalTime();

		/**
		 * The meta object literal for the '<em><b>Distance To Next Stop</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_CURRENT_STOP_POINT_DATA__DISTANCE_TO_NEXT_STOP = eINSTANCE.getCustomerInfoCurrentStopPointData_DistanceToNextStop();

		/**
		 * The meta object literal for the '<em><b>Fare Zone</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_CURRENT_STOP_POINT_DATA__FARE_ZONE = eINSTANCE.getCustomerInfoCurrentStopPointData_FareZone();

		/**
		 * The meta object literal for the '{@link de.jena.model.sensinact.ibis.impl.CustomerInfoTripDataImpl <em>Customer Info Trip Data</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.jena.model.sensinact.ibis.impl.CustomerInfoTripDataImpl
		 * @see de.jena.model.sensinact.ibis.impl.IbisSensinactPackageImpl#getCustomerInfoTripData()
		 * @generated
		 */
		EClass CUSTOMER_INFO_TRIP_DATA = eINSTANCE.getCustomerInfoTripData();

		/**
		 * The meta object literal for the '<em><b>Service Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_TRIP_DATA__SERVICE_NAME = eINSTANCE.getCustomerInfoTripData_ServiceName();

		/**
		 * The meta object literal for the '<em><b>Service Operation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_TRIP_DATA__SERVICE_OPERATION = eINSTANCE.getCustomerInfoTripData_ServiceOperation();

		/**
		 * The meta object literal for the '<em><b>Timestamp</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_TRIP_DATA__TIMESTAMP = eINSTANCE.getCustomerInfoTripData_Timestamp();

		/**
		 * The meta object literal for the '<em><b>Vehicle Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_TRIP_DATA__VEHICLE_REF = eINSTANCE.getCustomerInfoTripData_VehicleRef();

		/**
		 * The meta object literal for the '<em><b>Default Language</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_TRIP_DATA__DEFAULT_LANGUAGE = eINSTANCE.getCustomerInfoTripData_DefaultLanguage();

		/**
		 * The meta object literal for the '<em><b>Current Stop Index</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_TRIP_DATA__CURRENT_STOP_INDEX = eINSTANCE.getCustomerInfoTripData_CurrentStopIndex();

		/**
		 * The meta object literal for the '<em><b>Trip Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_TRIP_DATA__TRIP_REF = eINSTANCE.getCustomerInfoTripData_TripRef();

		/**
		 * The meta object literal for the '<em><b>Location State</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_TRIP_DATA__LOCATION_STATE = eINSTANCE.getCustomerInfoTripData_LocationState();

		/**
		 * The meta object literal for the '<em><b>Timetable Delay</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_TRIP_DATA__TIMETABLE_DELAY = eINSTANCE.getCustomerInfoTripData_TimetableDelay();

		/**
		 * The meta object literal for the '<em><b>Route Direction</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_TRIP_DATA__ROUTE_DIRECTION = eINSTANCE.getCustomerInfoTripData_RouteDirection();

		/**
		 * The meta object literal for the '<em><b>Run Number</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_TRIP_DATA__RUN_NUMBER = eINSTANCE.getCustomerInfoTripData_RunNumber();

		/**
		 * The meta object literal for the '<em><b>Pattern Number</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_TRIP_DATA__PATTERN_NUMBER = eINSTANCE.getCustomerInfoTripData_PatternNumber();

		/**
		 * The meta object literal for the '<em><b>Path Destination Number</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_TRIP_DATA__PATH_DESTINATION_NUMBER = eINSTANCE.getCustomerInfoTripData_PathDestinationNumber();

		/**
		 * The meta object literal for the '<em><b>Additional Text Msg</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_TRIP_DATA__ADDITIONAL_TEXT_MSG = eINSTANCE.getCustomerInfoTripData_AdditionalTextMsg();

		/**
		 * The meta object literal for the '<em><b>Additional Text Msg1</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_TRIP_DATA__ADDITIONAL_TEXT_MSG1 = eINSTANCE.getCustomerInfoTripData_AdditionalTextMsg1();

		/**
		 * The meta object literal for the '<em><b>Additional Text Msg2</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_TRIP_DATA__ADDITIONAL_TEXT_MSG2 = eINSTANCE.getCustomerInfoTripData_AdditionalTextMsg2();

		/**
		 * The meta object literal for the '<em><b>Additional Text Msg3</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_TRIP_DATA__ADDITIONAL_TEXT_MSG3 = eINSTANCE.getCustomerInfoTripData_AdditionalTextMsg3();

		/**
		 * The meta object literal for the '<em><b>Additional Text Msg4</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_TRIP_DATA__ADDITIONAL_TEXT_MSG4 = eINSTANCE.getCustomerInfoTripData_AdditionalTextMsg4();

		/**
		 * The meta object literal for the '<em><b>Additional Text Msg5</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_TRIP_DATA__ADDITIONAL_TEXT_MSG5 = eINSTANCE.getCustomerInfoTripData_AdditionalTextMsg5();

		/**
		 * The meta object literal for the '<em><b>Additional Text Msg6</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_TRIP_DATA__ADDITIONAL_TEXT_MSG6 = eINSTANCE.getCustomerInfoTripData_AdditionalTextMsg6();

		/**
		 * The meta object literal for the '<em><b>Additional Text Msg7</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_TRIP_DATA__ADDITIONAL_TEXT_MSG7 = eINSTANCE.getCustomerInfoTripData_AdditionalTextMsg7();

		/**
		 * The meta object literal for the '<em><b>Additional Text Msg8</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_TRIP_DATA__ADDITIONAL_TEXT_MSG8 = eINSTANCE.getCustomerInfoTripData_AdditionalTextMsg8();

		/**
		 * The meta object literal for the '<em><b>Additional Text Msg9</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_TRIP_DATA__ADDITIONAL_TEXT_MSG9 = eINSTANCE.getCustomerInfoTripData_AdditionalTextMsg9();

		/**
		 * The meta object literal for the '{@link de.jena.model.sensinact.ibis.impl.CustomerInfoVehicleDataImpl <em>Customer Info Vehicle Data</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.jena.model.sensinact.ibis.impl.CustomerInfoVehicleDataImpl
		 * @see de.jena.model.sensinact.ibis.impl.IbisSensinactPackageImpl#getCustomerInfoVehicleData()
		 * @generated
		 */
		EClass CUSTOMER_INFO_VEHICLE_DATA = eINSTANCE.getCustomerInfoVehicleData();

		/**
		 * The meta object literal for the '<em><b>Service Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_VEHICLE_DATA__SERVICE_NAME = eINSTANCE.getCustomerInfoVehicleData_ServiceName();

		/**
		 * The meta object literal for the '<em><b>Service Operation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_VEHICLE_DATA__SERVICE_OPERATION = eINSTANCE.getCustomerInfoVehicleData_ServiceOperation();

		/**
		 * The meta object literal for the '<em><b>Timestamp</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_VEHICLE_DATA__TIMESTAMP = eINSTANCE.getCustomerInfoVehicleData_Timestamp();

		/**
		 * The meta object literal for the '<em><b>Vehicle Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_VEHICLE_DATA__VEHICLE_REF = eINSTANCE.getCustomerInfoVehicleData_VehicleRef();

		/**
		 * The meta object literal for the '<em><b>Route Deviation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_VEHICLE_DATA__ROUTE_DEVIATION = eINSTANCE.getCustomerInfoVehicleData_RouteDeviation();

		/**
		 * The meta object literal for the '<em><b>Door State</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_VEHICLE_DATA__DOOR_STATE = eINSTANCE.getCustomerInfoVehicleData_DoorState();

		/**
		 * The meta object literal for the '<em><b>In Panic</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_VEHICLE_DATA__IN_PANIC = eINSTANCE.getCustomerInfoVehicleData_InPanic();

		/**
		 * The meta object literal for the '<em><b>Vehicle Stop Requested</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_VEHICLE_DATA__VEHICLE_STOP_REQUESTED = eINSTANCE.getCustomerInfoVehicleData_VehicleStopRequested();

		/**
		 * The meta object literal for the '<em><b>Exit Side</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_VEHICLE_DATA__EXIT_SIDE = eINSTANCE.getCustomerInfoVehicleData_ExitSide();

		/**
		 * The meta object literal for the '<em><b>Moving Direction Forward</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_VEHICLE_DATA__MOVING_DIRECTION_FORWARD = eINSTANCE.getCustomerInfoVehicleData_MovingDirectionForward();

		/**
		 * The meta object literal for the '<em><b>Vehicle Mode</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_VEHICLE_DATA__VEHICLE_MODE = eINSTANCE.getCustomerInfoVehicleData_VehicleMode();

		/**
		 * The meta object literal for the '<em><b>Speaker Active</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_VEHICLE_DATA__SPEAKER_ACTIVE = eINSTANCE.getCustomerInfoVehicleData_SpeakerActive();

		/**
		 * The meta object literal for the '<em><b>Stop Information Active</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_VEHICLE_DATA__STOP_INFORMATION_ACTIVE = eINSTANCE.getCustomerInfoVehicleData_StopInformationActive();

		/**
		 * The meta object literal for the '<em><b>Trip State</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_VEHICLE_DATA__TRIP_STATE = eINSTANCE.getCustomerInfoVehicleData_TripState();

		/**
		 * The meta object literal for the '{@link de.jena.model.sensinact.ibis.impl.CustomerInfoCurrentAnnouncementDataImpl <em>Customer Info Current Announcement Data</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.jena.model.sensinact.ibis.impl.CustomerInfoCurrentAnnouncementDataImpl
		 * @see de.jena.model.sensinact.ibis.impl.IbisSensinactPackageImpl#getCustomerInfoCurrentAnnouncementData()
		 * @generated
		 */
		EClass CUSTOMER_INFO_CURRENT_ANNOUNCEMENT_DATA = eINSTANCE.getCustomerInfoCurrentAnnouncementData();

		/**
		 * The meta object literal for the '<em><b>Service Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_CURRENT_ANNOUNCEMENT_DATA__SERVICE_NAME = eINSTANCE.getCustomerInfoCurrentAnnouncementData_ServiceName();

		/**
		 * The meta object literal for the '<em><b>Service Operation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_CURRENT_ANNOUNCEMENT_DATA__SERVICE_OPERATION = eINSTANCE.getCustomerInfoCurrentAnnouncementData_ServiceOperation();

		/**
		 * The meta object literal for the '<em><b>Timestamp</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_CURRENT_ANNOUNCEMENT_DATA__TIMESTAMP = eINSTANCE.getCustomerInfoCurrentAnnouncementData_Timestamp();

		/**
		 * The meta object literal for the '<em><b>Announcement Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_CURRENT_ANNOUNCEMENT_DATA__ANNOUNCEMENT_REF = eINSTANCE.getCustomerInfoCurrentAnnouncementData_AnnouncementRef();

		/**
		 * The meta object literal for the '<em><b>Announcement Text</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_CURRENT_ANNOUNCEMENT_DATA__ANNOUNCEMENT_TEXT = eINSTANCE.getCustomerInfoCurrentAnnouncementData_AnnouncementText();

		/**
		 * The meta object literal for the '<em><b>Announcement TTS Text</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_CURRENT_ANNOUNCEMENT_DATA__ANNOUNCEMENT_TTS_TEXT = eINSTANCE.getCustomerInfoCurrentAnnouncementData_AnnouncementTTSText();

		/**
		 * The meta object literal for the '{@link de.jena.model.sensinact.ibis.impl.CustomerInfoCurrentConnectionDataImpl <em>Customer Info Current Connection Data</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.jena.model.sensinact.ibis.impl.CustomerInfoCurrentConnectionDataImpl
		 * @see de.jena.model.sensinact.ibis.impl.IbisSensinactPackageImpl#getCustomerInfoCurrentConnectionData()
		 * @generated
		 */
		EClass CUSTOMER_INFO_CURRENT_CONNECTION_DATA = eINSTANCE.getCustomerInfoCurrentConnectionData();

		/**
		 * The meta object literal for the '<em><b>Service Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_CURRENT_CONNECTION_DATA__SERVICE_NAME = eINSTANCE.getCustomerInfoCurrentConnectionData_ServiceName();

		/**
		 * The meta object literal for the '<em><b>Service Operation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_CURRENT_CONNECTION_DATA__SERVICE_OPERATION = eINSTANCE.getCustomerInfoCurrentConnectionData_ServiceOperation();

		/**
		 * The meta object literal for the '<em><b>Timestamp</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_CURRENT_CONNECTION_DATA__TIMESTAMP = eINSTANCE.getCustomerInfoCurrentConnectionData_Timestamp();

		/**
		 * The meta object literal for the '{@link de.jena.model.sensinact.ibis.impl.CustomerInfoCurrentDisplayContentDataImpl <em>Customer Info Current Display Content Data</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.jena.model.sensinact.ibis.impl.CustomerInfoCurrentDisplayContentDataImpl
		 * @see de.jena.model.sensinact.ibis.impl.IbisSensinactPackageImpl#getCustomerInfoCurrentDisplayContentData()
		 * @generated
		 */
		EClass CUSTOMER_INFO_CURRENT_DISPLAY_CONTENT_DATA = eINSTANCE.getCustomerInfoCurrentDisplayContentData();

		/**
		 * The meta object literal for the '<em><b>Service Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_CURRENT_DISPLAY_CONTENT_DATA__SERVICE_NAME = eINSTANCE.getCustomerInfoCurrentDisplayContentData_ServiceName();

		/**
		 * The meta object literal for the '<em><b>Service Operation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_CURRENT_DISPLAY_CONTENT_DATA__SERVICE_OPERATION = eINSTANCE.getCustomerInfoCurrentDisplayContentData_ServiceOperation();

		/**
		 * The meta object literal for the '<em><b>Timestamp</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_INFO_CURRENT_DISPLAY_CONTENT_DATA__TIMESTAMP = eINSTANCE.getCustomerInfoCurrentDisplayContentData_Timestamp();

		/**
		 * The meta object literal for the '{@link de.jena.model.sensinact.ibis.impl.IbisAdminImpl <em>Ibis Admin</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.jena.model.sensinact.ibis.impl.IbisAdminImpl
		 * @see de.jena.model.sensinact.ibis.impl.IbisSensinactPackageImpl#getIbisAdmin()
		 * @generated
		 */
		EClass IBIS_ADMIN = eINSTANCE.getIbisAdmin();

		/**
		 * The meta object literal for the '<em><b>Device Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IBIS_ADMIN__DEVICE_TYPE = eINSTANCE.getIbisAdmin_DeviceType();

	}

} //IbisSensinactPackage
