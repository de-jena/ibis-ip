/**
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
package de.jena.model.sensinact.ibis.impl;

import de.jena.model.sensinact.ibis.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class IbisSensinactFactoryImpl extends EFactoryImpl implements IbisSensinactFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static IbisSensinactFactory init() {
		try {
			IbisSensinactFactory theIbisSensinactFactory = (IbisSensinactFactory)EPackage.Registry.INSTANCE.getEFactory(IbisSensinactPackage.eNS_URI);
			if (theIbisSensinactFactory != null) {
				return theIbisSensinactFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new IbisSensinactFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IbisSensinactFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case IbisSensinactPackage.IBIS_DEVICE: return createIbisDevice();
			case IbisSensinactPackage.CUSTOMER_INFO_ALL_DATA: return createCustomerInfoAllData();
			case IbisSensinactPackage.CUSTOMER_INFO_CURRENT_STOP_INDEX_DATA: return createCustomerInfoCurrentStopIndexData();
			case IbisSensinactPackage.CUSTOMER_INFO_CURRENT_STOP_POINT_DATA: return createCustomerInfoCurrentStopPointData();
			case IbisSensinactPackage.CUSTOMER_INFO_TRIP_DATA: return createCustomerInfoTripData();
			case IbisSensinactPackage.CUSTOMER_INFO_VEHICLE_DATA: return createCustomerInfoVehicleData();
			case IbisSensinactPackage.CUSTOMER_INFO_CURRENT_ANNOUNCEMENT_DATA: return createCustomerInfoCurrentAnnouncementData();
			case IbisSensinactPackage.CUSTOMER_INFO_CURRENT_CONNECTION_DATA: return createCustomerInfoCurrentConnectionData();
			case IbisSensinactPackage.CUSTOMER_INFO_CURRENT_DISPLAY_CONTENT_DATA: return createCustomerInfoCurrentDisplayContentData();
			case IbisSensinactPackage.IBIS_ADMIN: return createIbisAdmin();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public IbisDevice createIbisDevice() {
		IbisDeviceImpl ibisDevice = new IbisDeviceImpl();
		return ibisDevice;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CustomerInfoAllData createCustomerInfoAllData() {
		CustomerInfoAllDataImpl customerInfoAllData = new CustomerInfoAllDataImpl();
		return customerInfoAllData;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CustomerInfoCurrentStopIndexData createCustomerInfoCurrentStopIndexData() {
		CustomerInfoCurrentStopIndexDataImpl customerInfoCurrentStopIndexData = new CustomerInfoCurrentStopIndexDataImpl();
		return customerInfoCurrentStopIndexData;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CustomerInfoCurrentStopPointData createCustomerInfoCurrentStopPointData() {
		CustomerInfoCurrentStopPointDataImpl customerInfoCurrentStopPointData = new CustomerInfoCurrentStopPointDataImpl();
		return customerInfoCurrentStopPointData;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CustomerInfoTripData createCustomerInfoTripData() {
		CustomerInfoTripDataImpl customerInfoTripData = new CustomerInfoTripDataImpl();
		return customerInfoTripData;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CustomerInfoVehicleData createCustomerInfoVehicleData() {
		CustomerInfoVehicleDataImpl customerInfoVehicleData = new CustomerInfoVehicleDataImpl();
		return customerInfoVehicleData;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CustomerInfoCurrentAnnouncementData createCustomerInfoCurrentAnnouncementData() {
		CustomerInfoCurrentAnnouncementDataImpl customerInfoCurrentAnnouncementData = new CustomerInfoCurrentAnnouncementDataImpl();
		return customerInfoCurrentAnnouncementData;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CustomerInfoCurrentConnectionData createCustomerInfoCurrentConnectionData() {
		CustomerInfoCurrentConnectionDataImpl customerInfoCurrentConnectionData = new CustomerInfoCurrentConnectionDataImpl();
		return customerInfoCurrentConnectionData;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CustomerInfoCurrentDisplayContentData createCustomerInfoCurrentDisplayContentData() {
		CustomerInfoCurrentDisplayContentDataImpl customerInfoCurrentDisplayContentData = new CustomerInfoCurrentDisplayContentDataImpl();
		return customerInfoCurrentDisplayContentData;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public IbisAdmin createIbisAdmin() {
		IbisAdminImpl ibisAdmin = new IbisAdminImpl();
		return ibisAdmin;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public IbisSensinactPackage getIbisSensinactPackage() {
		return (IbisSensinactPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static IbisSensinactPackage getPackage() {
		return IbisSensinactPackage.eINSTANCE;
	}

} //IbisSensinactFactoryImpl
