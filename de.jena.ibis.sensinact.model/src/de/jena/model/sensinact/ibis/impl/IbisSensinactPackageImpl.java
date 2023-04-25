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

import de.jena.model.sensinact.ibis.CustomerInfoAllData;
import de.jena.model.sensinact.ibis.CustomerInfoCurrentAnnouncementData;
import de.jena.model.sensinact.ibis.CustomerInfoCurrentConnectionData;
import de.jena.model.sensinact.ibis.CustomerInfoCurrentDisplayContentData;
import de.jena.model.sensinact.ibis.CustomerInfoCurrentStopIndexData;
import de.jena.model.sensinact.ibis.CustomerInfoCurrentStopPointData;
import de.jena.model.sensinact.ibis.CustomerInfoTripData;
import de.jena.model.sensinact.ibis.CustomerInfoVehicleData;
import de.jena.model.sensinact.ibis.IbisAdmin;
import de.jena.model.sensinact.ibis.IbisDevice;
import de.jena.model.sensinact.ibis.IbisSensinactFactory;
import de.jena.model.sensinact.ibis.IbisSensinactPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.sensinact.model.core.provider.ProviderPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class IbisSensinactPackageImpl extends EPackageImpl implements IbisSensinactPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ibisDeviceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass customerInfoAllDataEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass customerInfoCurrentStopIndexDataEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass customerInfoCurrentStopPointDataEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass customerInfoTripDataEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass customerInfoVehicleDataEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass customerInfoCurrentAnnouncementDataEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass customerInfoCurrentConnectionDataEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass customerInfoCurrentDisplayContentDataEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ibisAdminEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see de.jena.model.sensinact.ibis.IbisSensinactPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private IbisSensinactPackageImpl() {
		super(eNS_URI, IbisSensinactFactory.eINSTANCE);
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 *
	 * <p>This method is used to initialize {@link IbisSensinactPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static IbisSensinactPackage init() {
		if (isInited) return (IbisSensinactPackage)EPackage.Registry.INSTANCE.getEPackage(IbisSensinactPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredIbisSensinactPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		IbisSensinactPackageImpl theIbisSensinactPackage = registeredIbisSensinactPackage instanceof IbisSensinactPackageImpl ? (IbisSensinactPackageImpl)registeredIbisSensinactPackage : new IbisSensinactPackageImpl();

		isInited = true;

		// Initialize simple dependencies
		ProviderPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theIbisSensinactPackage.createPackageContents();

		// Initialize created meta-data
		theIbisSensinactPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theIbisSensinactPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(IbisSensinactPackage.eNS_URI, theIbisSensinactPackage);
		return theIbisSensinactPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getIbisDevice() {
		return ibisDeviceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getIbisDevice_CustomerInfoAllData() {
		return (EReference)ibisDeviceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getIbisDevice_CustomerInfoCurrentStopIndexData() {
		return (EReference)ibisDeviceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getIbisDevice_CustomerInfoCurrentStopPointData() {
		return (EReference)ibisDeviceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getIbisDevice_CustomerInfoTripData() {
		return (EReference)ibisDeviceEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getIbisDevice_CustomerInfoVehicleData() {
		return (EReference)ibisDeviceEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getIbisDevice_CustomerInfoCurrentAnnouncementData() {
		return (EReference)ibisDeviceEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getIbisDevice_CustomerInfoCurrentConnectionData() {
		return (EReference)ibisDeviceEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getIbisDevice_CustomerInfoCurrentDisplayContentData() {
		return (EReference)ibisDeviceEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCustomerInfoAllData() {
		return customerInfoAllDataEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoAllData_ServiceName() {
		return (EAttribute)customerInfoAllDataEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoAllData_ServiceOperation() {
		return (EAttribute)customerInfoAllDataEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoAllData_Timestamp() {
		return (EAttribute)customerInfoAllDataEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoAllData_VehicleRef() {
		return (EAttribute)customerInfoAllDataEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoAllData_DefaultLanguage() {
		return (EAttribute)customerInfoAllDataEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoAllData_CurrentStopIndex() {
		return (EAttribute)customerInfoAllDataEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoAllData_RouteDeviation() {
		return (EAttribute)customerInfoAllDataEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoAllData_DoorState() {
		return (EAttribute)customerInfoAllDataEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoAllData_InPanic() {
		return (EAttribute)customerInfoAllDataEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoAllData_VehicleStopRequested() {
		return (EAttribute)customerInfoAllDataEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoAllData_ExitSide() {
		return (EAttribute)customerInfoAllDataEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoAllData_MovingDirectionForward() {
		return (EAttribute)customerInfoAllDataEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoAllData_VehicleMode() {
		return (EAttribute)customerInfoAllDataEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoAllData_SpeakerActive() {
		return (EAttribute)customerInfoAllDataEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoAllData_StopInformationActive() {
		return (EAttribute)customerInfoAllDataEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoAllData_TripState() {
		return (EAttribute)customerInfoAllDataEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCustomerInfoCurrentStopIndexData() {
		return customerInfoCurrentStopIndexDataEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoCurrentStopIndexData_ServiceName() {
		return (EAttribute)customerInfoCurrentStopIndexDataEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoCurrentStopIndexData_ServiceOperation() {
		return (EAttribute)customerInfoCurrentStopIndexDataEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoCurrentStopIndexData_Timestamp() {
		return (EAttribute)customerInfoCurrentStopIndexDataEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoCurrentStopIndexData_CurrentStopIndex() {
		return (EAttribute)customerInfoCurrentStopIndexDataEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCustomerInfoCurrentStopPointData() {
		return customerInfoCurrentStopPointDataEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoCurrentStopPointData_ServiceName() {
		return (EAttribute)customerInfoCurrentStopPointDataEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoCurrentStopPointData_ServiceOperation() {
		return (EAttribute)customerInfoCurrentStopPointDataEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoCurrentStopPointData_Timestamp() {
		return (EAttribute)customerInfoCurrentStopPointDataEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoCurrentStopPointData_StopIndex() {
		return (EAttribute)customerInfoCurrentStopPointDataEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoCurrentStopPointData_StopRef() {
		return (EAttribute)customerInfoCurrentStopPointDataEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoCurrentStopPointData_StopName() {
		return (EAttribute)customerInfoCurrentStopPointDataEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoCurrentStopPointData_StopAlternativeName() {
		return (EAttribute)customerInfoCurrentStopPointDataEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoCurrentStopPointData_Platform() {
		return (EAttribute)customerInfoCurrentStopPointDataEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoCurrentStopPointData_ArrivalScheduled() {
		return (EAttribute)customerInfoCurrentStopPointDataEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoCurrentStopPointData_ArrivalExpected() {
		return (EAttribute)customerInfoCurrentStopPointDataEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoCurrentStopPointData_DepartureScheduled() {
		return (EAttribute)customerInfoCurrentStopPointDataEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoCurrentStopPointData_DepartureExpected() {
		return (EAttribute)customerInfoCurrentStopPointDataEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoCurrentStopPointData_RecordedArrivalTime() {
		return (EAttribute)customerInfoCurrentStopPointDataEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoCurrentStopPointData_DistanceToNextStop() {
		return (EAttribute)customerInfoCurrentStopPointDataEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoCurrentStopPointData_FareZone() {
		return (EAttribute)customerInfoCurrentStopPointDataEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCustomerInfoTripData() {
		return customerInfoTripDataEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoTripData_ServiceName() {
		return (EAttribute)customerInfoTripDataEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoTripData_ServiceOperation() {
		return (EAttribute)customerInfoTripDataEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoTripData_Timestamp() {
		return (EAttribute)customerInfoTripDataEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoTripData_VehicleRef() {
		return (EAttribute)customerInfoTripDataEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoTripData_DefaultLanguage() {
		return (EAttribute)customerInfoTripDataEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoTripData_CurrentStopIndex() {
		return (EAttribute)customerInfoTripDataEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoTripData_TripRef() {
		return (EAttribute)customerInfoTripDataEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoTripData_LocationState() {
		return (EAttribute)customerInfoTripDataEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoTripData_TimetableDelay() {
		return (EAttribute)customerInfoTripDataEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoTripData_RouteDirection() {
		return (EAttribute)customerInfoTripDataEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoTripData_RunNumber() {
		return (EAttribute)customerInfoTripDataEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoTripData_PatternNumber() {
		return (EAttribute)customerInfoTripDataEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoTripData_PathDestinationNumber() {
		return (EAttribute)customerInfoTripDataEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoTripData_AdditionalTextMsg() {
		return (EAttribute)customerInfoTripDataEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoTripData_AdditionalTextMsg1() {
		return (EAttribute)customerInfoTripDataEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoTripData_AdditionalTextMsg2() {
		return (EAttribute)customerInfoTripDataEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoTripData_AdditionalTextMsg3() {
		return (EAttribute)customerInfoTripDataEClass.getEStructuralFeatures().get(16);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoTripData_AdditionalTextMsg4() {
		return (EAttribute)customerInfoTripDataEClass.getEStructuralFeatures().get(17);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoTripData_AdditionalTextMsg5() {
		return (EAttribute)customerInfoTripDataEClass.getEStructuralFeatures().get(18);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoTripData_AdditionalTextMsg6() {
		return (EAttribute)customerInfoTripDataEClass.getEStructuralFeatures().get(19);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoTripData_AdditionalTextMsg7() {
		return (EAttribute)customerInfoTripDataEClass.getEStructuralFeatures().get(20);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoTripData_AdditionalTextMsg8() {
		return (EAttribute)customerInfoTripDataEClass.getEStructuralFeatures().get(21);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoTripData_AdditionalTextMsg9() {
		return (EAttribute)customerInfoTripDataEClass.getEStructuralFeatures().get(22);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCustomerInfoVehicleData() {
		return customerInfoVehicleDataEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoVehicleData_ServiceName() {
		return (EAttribute)customerInfoVehicleDataEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoVehicleData_ServiceOperation() {
		return (EAttribute)customerInfoVehicleDataEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoVehicleData_Timestamp() {
		return (EAttribute)customerInfoVehicleDataEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoVehicleData_VehicleRef() {
		return (EAttribute)customerInfoVehicleDataEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoVehicleData_RouteDeviation() {
		return (EAttribute)customerInfoVehicleDataEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoVehicleData_DoorState() {
		return (EAttribute)customerInfoVehicleDataEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoVehicleData_InPanic() {
		return (EAttribute)customerInfoVehicleDataEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoVehicleData_VehicleStopRequested() {
		return (EAttribute)customerInfoVehicleDataEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoVehicleData_ExitSide() {
		return (EAttribute)customerInfoVehicleDataEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoVehicleData_MovingDirectionForward() {
		return (EAttribute)customerInfoVehicleDataEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoVehicleData_VehicleMode() {
		return (EAttribute)customerInfoVehicleDataEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoVehicleData_SpeakerActive() {
		return (EAttribute)customerInfoVehicleDataEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoVehicleData_StopInformationActive() {
		return (EAttribute)customerInfoVehicleDataEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoVehicleData_TripState() {
		return (EAttribute)customerInfoVehicleDataEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCustomerInfoCurrentAnnouncementData() {
		return customerInfoCurrentAnnouncementDataEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoCurrentAnnouncementData_ServiceName() {
		return (EAttribute)customerInfoCurrentAnnouncementDataEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoCurrentAnnouncementData_ServiceOperation() {
		return (EAttribute)customerInfoCurrentAnnouncementDataEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoCurrentAnnouncementData_Timestamp() {
		return (EAttribute)customerInfoCurrentAnnouncementDataEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoCurrentAnnouncementData_AnnouncementRef() {
		return (EAttribute)customerInfoCurrentAnnouncementDataEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoCurrentAnnouncementData_AnnouncementText() {
		return (EAttribute)customerInfoCurrentAnnouncementDataEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoCurrentAnnouncementData_AnnouncementTTSText() {
		return (EAttribute)customerInfoCurrentAnnouncementDataEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCustomerInfoCurrentConnectionData() {
		return customerInfoCurrentConnectionDataEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoCurrentConnectionData_ServiceName() {
		return (EAttribute)customerInfoCurrentConnectionDataEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoCurrentConnectionData_ServiceOperation() {
		return (EAttribute)customerInfoCurrentConnectionDataEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoCurrentConnectionData_Timestamp() {
		return (EAttribute)customerInfoCurrentConnectionDataEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCustomerInfoCurrentDisplayContentData() {
		return customerInfoCurrentDisplayContentDataEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoCurrentDisplayContentData_ServiceName() {
		return (EAttribute)customerInfoCurrentDisplayContentDataEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoCurrentDisplayContentData_ServiceOperation() {
		return (EAttribute)customerInfoCurrentDisplayContentDataEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomerInfoCurrentDisplayContentData_Timestamp() {
		return (EAttribute)customerInfoCurrentDisplayContentDataEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getIbisAdmin() {
		return ibisAdminEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getIbisAdmin_DeviceType() {
		return (EAttribute)ibisAdminEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public IbisSensinactFactory getIbisSensinactFactory() {
		return (IbisSensinactFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		ibisDeviceEClass = createEClass(IBIS_DEVICE);
		createEReference(ibisDeviceEClass, IBIS_DEVICE__CUSTOMER_INFO_ALL_DATA);
		createEReference(ibisDeviceEClass, IBIS_DEVICE__CUSTOMER_INFO_CURRENT_STOP_INDEX_DATA);
		createEReference(ibisDeviceEClass, IBIS_DEVICE__CUSTOMER_INFO_CURRENT_STOP_POINT_DATA);
		createEReference(ibisDeviceEClass, IBIS_DEVICE__CUSTOMER_INFO_TRIP_DATA);
		createEReference(ibisDeviceEClass, IBIS_DEVICE__CUSTOMER_INFO_VEHICLE_DATA);
		createEReference(ibisDeviceEClass, IBIS_DEVICE__CUSTOMER_INFO_CURRENT_ANNOUNCEMENT_DATA);
		createEReference(ibisDeviceEClass, IBIS_DEVICE__CUSTOMER_INFO_CURRENT_CONNECTION_DATA);
		createEReference(ibisDeviceEClass, IBIS_DEVICE__CUSTOMER_INFO_CURRENT_DISPLAY_CONTENT_DATA);

		customerInfoAllDataEClass = createEClass(CUSTOMER_INFO_ALL_DATA);
		createEAttribute(customerInfoAllDataEClass, CUSTOMER_INFO_ALL_DATA__SERVICE_NAME);
		createEAttribute(customerInfoAllDataEClass, CUSTOMER_INFO_ALL_DATA__SERVICE_OPERATION);
		createEAttribute(customerInfoAllDataEClass, CUSTOMER_INFO_ALL_DATA__TIMESTAMP);
		createEAttribute(customerInfoAllDataEClass, CUSTOMER_INFO_ALL_DATA__VEHICLE_REF);
		createEAttribute(customerInfoAllDataEClass, CUSTOMER_INFO_ALL_DATA__DEFAULT_LANGUAGE);
		createEAttribute(customerInfoAllDataEClass, CUSTOMER_INFO_ALL_DATA__CURRENT_STOP_INDEX);
		createEAttribute(customerInfoAllDataEClass, CUSTOMER_INFO_ALL_DATA__ROUTE_DEVIATION);
		createEAttribute(customerInfoAllDataEClass, CUSTOMER_INFO_ALL_DATA__DOOR_STATE);
		createEAttribute(customerInfoAllDataEClass, CUSTOMER_INFO_ALL_DATA__IN_PANIC);
		createEAttribute(customerInfoAllDataEClass, CUSTOMER_INFO_ALL_DATA__VEHICLE_STOP_REQUESTED);
		createEAttribute(customerInfoAllDataEClass, CUSTOMER_INFO_ALL_DATA__EXIT_SIDE);
		createEAttribute(customerInfoAllDataEClass, CUSTOMER_INFO_ALL_DATA__MOVING_DIRECTION_FORWARD);
		createEAttribute(customerInfoAllDataEClass, CUSTOMER_INFO_ALL_DATA__VEHICLE_MODE);
		createEAttribute(customerInfoAllDataEClass, CUSTOMER_INFO_ALL_DATA__SPEAKER_ACTIVE);
		createEAttribute(customerInfoAllDataEClass, CUSTOMER_INFO_ALL_DATA__STOP_INFORMATION_ACTIVE);
		createEAttribute(customerInfoAllDataEClass, CUSTOMER_INFO_ALL_DATA__TRIP_STATE);

		customerInfoCurrentStopIndexDataEClass = createEClass(CUSTOMER_INFO_CURRENT_STOP_INDEX_DATA);
		createEAttribute(customerInfoCurrentStopIndexDataEClass, CUSTOMER_INFO_CURRENT_STOP_INDEX_DATA__SERVICE_NAME);
		createEAttribute(customerInfoCurrentStopIndexDataEClass, CUSTOMER_INFO_CURRENT_STOP_INDEX_DATA__SERVICE_OPERATION);
		createEAttribute(customerInfoCurrentStopIndexDataEClass, CUSTOMER_INFO_CURRENT_STOP_INDEX_DATA__TIMESTAMP);
		createEAttribute(customerInfoCurrentStopIndexDataEClass, CUSTOMER_INFO_CURRENT_STOP_INDEX_DATA__CURRENT_STOP_INDEX);

		customerInfoCurrentStopPointDataEClass = createEClass(CUSTOMER_INFO_CURRENT_STOP_POINT_DATA);
		createEAttribute(customerInfoCurrentStopPointDataEClass, CUSTOMER_INFO_CURRENT_STOP_POINT_DATA__SERVICE_NAME);
		createEAttribute(customerInfoCurrentStopPointDataEClass, CUSTOMER_INFO_CURRENT_STOP_POINT_DATA__SERVICE_OPERATION);
		createEAttribute(customerInfoCurrentStopPointDataEClass, CUSTOMER_INFO_CURRENT_STOP_POINT_DATA__TIMESTAMP);
		createEAttribute(customerInfoCurrentStopPointDataEClass, CUSTOMER_INFO_CURRENT_STOP_POINT_DATA__STOP_INDEX);
		createEAttribute(customerInfoCurrentStopPointDataEClass, CUSTOMER_INFO_CURRENT_STOP_POINT_DATA__STOP_REF);
		createEAttribute(customerInfoCurrentStopPointDataEClass, CUSTOMER_INFO_CURRENT_STOP_POINT_DATA__STOP_NAME);
		createEAttribute(customerInfoCurrentStopPointDataEClass, CUSTOMER_INFO_CURRENT_STOP_POINT_DATA__STOP_ALTERNATIVE_NAME);
		createEAttribute(customerInfoCurrentStopPointDataEClass, CUSTOMER_INFO_CURRENT_STOP_POINT_DATA__PLATFORM);
		createEAttribute(customerInfoCurrentStopPointDataEClass, CUSTOMER_INFO_CURRENT_STOP_POINT_DATA__ARRIVAL_SCHEDULED);
		createEAttribute(customerInfoCurrentStopPointDataEClass, CUSTOMER_INFO_CURRENT_STOP_POINT_DATA__ARRIVAL_EXPECTED);
		createEAttribute(customerInfoCurrentStopPointDataEClass, CUSTOMER_INFO_CURRENT_STOP_POINT_DATA__DEPARTURE_SCHEDULED);
		createEAttribute(customerInfoCurrentStopPointDataEClass, CUSTOMER_INFO_CURRENT_STOP_POINT_DATA__DEPARTURE_EXPECTED);
		createEAttribute(customerInfoCurrentStopPointDataEClass, CUSTOMER_INFO_CURRENT_STOP_POINT_DATA__RECORDED_ARRIVAL_TIME);
		createEAttribute(customerInfoCurrentStopPointDataEClass, CUSTOMER_INFO_CURRENT_STOP_POINT_DATA__DISTANCE_TO_NEXT_STOP);
		createEAttribute(customerInfoCurrentStopPointDataEClass, CUSTOMER_INFO_CURRENT_STOP_POINT_DATA__FARE_ZONE);

		customerInfoTripDataEClass = createEClass(CUSTOMER_INFO_TRIP_DATA);
		createEAttribute(customerInfoTripDataEClass, CUSTOMER_INFO_TRIP_DATA__SERVICE_NAME);
		createEAttribute(customerInfoTripDataEClass, CUSTOMER_INFO_TRIP_DATA__SERVICE_OPERATION);
		createEAttribute(customerInfoTripDataEClass, CUSTOMER_INFO_TRIP_DATA__TIMESTAMP);
		createEAttribute(customerInfoTripDataEClass, CUSTOMER_INFO_TRIP_DATA__VEHICLE_REF);
		createEAttribute(customerInfoTripDataEClass, CUSTOMER_INFO_TRIP_DATA__DEFAULT_LANGUAGE);
		createEAttribute(customerInfoTripDataEClass, CUSTOMER_INFO_TRIP_DATA__CURRENT_STOP_INDEX);
		createEAttribute(customerInfoTripDataEClass, CUSTOMER_INFO_TRIP_DATA__TRIP_REF);
		createEAttribute(customerInfoTripDataEClass, CUSTOMER_INFO_TRIP_DATA__LOCATION_STATE);
		createEAttribute(customerInfoTripDataEClass, CUSTOMER_INFO_TRIP_DATA__TIMETABLE_DELAY);
		createEAttribute(customerInfoTripDataEClass, CUSTOMER_INFO_TRIP_DATA__ROUTE_DIRECTION);
		createEAttribute(customerInfoTripDataEClass, CUSTOMER_INFO_TRIP_DATA__RUN_NUMBER);
		createEAttribute(customerInfoTripDataEClass, CUSTOMER_INFO_TRIP_DATA__PATTERN_NUMBER);
		createEAttribute(customerInfoTripDataEClass, CUSTOMER_INFO_TRIP_DATA__PATH_DESTINATION_NUMBER);
		createEAttribute(customerInfoTripDataEClass, CUSTOMER_INFO_TRIP_DATA__ADDITIONAL_TEXT_MSG);
		createEAttribute(customerInfoTripDataEClass, CUSTOMER_INFO_TRIP_DATA__ADDITIONAL_TEXT_MSG1);
		createEAttribute(customerInfoTripDataEClass, CUSTOMER_INFO_TRIP_DATA__ADDITIONAL_TEXT_MSG2);
		createEAttribute(customerInfoTripDataEClass, CUSTOMER_INFO_TRIP_DATA__ADDITIONAL_TEXT_MSG3);
		createEAttribute(customerInfoTripDataEClass, CUSTOMER_INFO_TRIP_DATA__ADDITIONAL_TEXT_MSG4);
		createEAttribute(customerInfoTripDataEClass, CUSTOMER_INFO_TRIP_DATA__ADDITIONAL_TEXT_MSG5);
		createEAttribute(customerInfoTripDataEClass, CUSTOMER_INFO_TRIP_DATA__ADDITIONAL_TEXT_MSG6);
		createEAttribute(customerInfoTripDataEClass, CUSTOMER_INFO_TRIP_DATA__ADDITIONAL_TEXT_MSG7);
		createEAttribute(customerInfoTripDataEClass, CUSTOMER_INFO_TRIP_DATA__ADDITIONAL_TEXT_MSG8);
		createEAttribute(customerInfoTripDataEClass, CUSTOMER_INFO_TRIP_DATA__ADDITIONAL_TEXT_MSG9);

		customerInfoVehicleDataEClass = createEClass(CUSTOMER_INFO_VEHICLE_DATA);
		createEAttribute(customerInfoVehicleDataEClass, CUSTOMER_INFO_VEHICLE_DATA__SERVICE_NAME);
		createEAttribute(customerInfoVehicleDataEClass, CUSTOMER_INFO_VEHICLE_DATA__SERVICE_OPERATION);
		createEAttribute(customerInfoVehicleDataEClass, CUSTOMER_INFO_VEHICLE_DATA__TIMESTAMP);
		createEAttribute(customerInfoVehicleDataEClass, CUSTOMER_INFO_VEHICLE_DATA__VEHICLE_REF);
		createEAttribute(customerInfoVehicleDataEClass, CUSTOMER_INFO_VEHICLE_DATA__ROUTE_DEVIATION);
		createEAttribute(customerInfoVehicleDataEClass, CUSTOMER_INFO_VEHICLE_DATA__DOOR_STATE);
		createEAttribute(customerInfoVehicleDataEClass, CUSTOMER_INFO_VEHICLE_DATA__IN_PANIC);
		createEAttribute(customerInfoVehicleDataEClass, CUSTOMER_INFO_VEHICLE_DATA__VEHICLE_STOP_REQUESTED);
		createEAttribute(customerInfoVehicleDataEClass, CUSTOMER_INFO_VEHICLE_DATA__EXIT_SIDE);
		createEAttribute(customerInfoVehicleDataEClass, CUSTOMER_INFO_VEHICLE_DATA__MOVING_DIRECTION_FORWARD);
		createEAttribute(customerInfoVehicleDataEClass, CUSTOMER_INFO_VEHICLE_DATA__VEHICLE_MODE);
		createEAttribute(customerInfoVehicleDataEClass, CUSTOMER_INFO_VEHICLE_DATA__SPEAKER_ACTIVE);
		createEAttribute(customerInfoVehicleDataEClass, CUSTOMER_INFO_VEHICLE_DATA__STOP_INFORMATION_ACTIVE);
		createEAttribute(customerInfoVehicleDataEClass, CUSTOMER_INFO_VEHICLE_DATA__TRIP_STATE);

		customerInfoCurrentAnnouncementDataEClass = createEClass(CUSTOMER_INFO_CURRENT_ANNOUNCEMENT_DATA);
		createEAttribute(customerInfoCurrentAnnouncementDataEClass, CUSTOMER_INFO_CURRENT_ANNOUNCEMENT_DATA__SERVICE_NAME);
		createEAttribute(customerInfoCurrentAnnouncementDataEClass, CUSTOMER_INFO_CURRENT_ANNOUNCEMENT_DATA__SERVICE_OPERATION);
		createEAttribute(customerInfoCurrentAnnouncementDataEClass, CUSTOMER_INFO_CURRENT_ANNOUNCEMENT_DATA__TIMESTAMP);
		createEAttribute(customerInfoCurrentAnnouncementDataEClass, CUSTOMER_INFO_CURRENT_ANNOUNCEMENT_DATA__ANNOUNCEMENT_REF);
		createEAttribute(customerInfoCurrentAnnouncementDataEClass, CUSTOMER_INFO_CURRENT_ANNOUNCEMENT_DATA__ANNOUNCEMENT_TEXT);
		createEAttribute(customerInfoCurrentAnnouncementDataEClass, CUSTOMER_INFO_CURRENT_ANNOUNCEMENT_DATA__ANNOUNCEMENT_TTS_TEXT);

		customerInfoCurrentConnectionDataEClass = createEClass(CUSTOMER_INFO_CURRENT_CONNECTION_DATA);
		createEAttribute(customerInfoCurrentConnectionDataEClass, CUSTOMER_INFO_CURRENT_CONNECTION_DATA__SERVICE_NAME);
		createEAttribute(customerInfoCurrentConnectionDataEClass, CUSTOMER_INFO_CURRENT_CONNECTION_DATA__SERVICE_OPERATION);
		createEAttribute(customerInfoCurrentConnectionDataEClass, CUSTOMER_INFO_CURRENT_CONNECTION_DATA__TIMESTAMP);

		customerInfoCurrentDisplayContentDataEClass = createEClass(CUSTOMER_INFO_CURRENT_DISPLAY_CONTENT_DATA);
		createEAttribute(customerInfoCurrentDisplayContentDataEClass, CUSTOMER_INFO_CURRENT_DISPLAY_CONTENT_DATA__SERVICE_NAME);
		createEAttribute(customerInfoCurrentDisplayContentDataEClass, CUSTOMER_INFO_CURRENT_DISPLAY_CONTENT_DATA__SERVICE_OPERATION);
		createEAttribute(customerInfoCurrentDisplayContentDataEClass, CUSTOMER_INFO_CURRENT_DISPLAY_CONTENT_DATA__TIMESTAMP);

		ibisAdminEClass = createEClass(IBIS_ADMIN);
		createEAttribute(ibisAdminEClass, IBIS_ADMIN__DEVICE_TYPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		ProviderPackage theProviderPackage = (ProviderPackage)EPackage.Registry.INSTANCE.getEPackage(ProviderPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		ibisDeviceEClass.getESuperTypes().add(theProviderPackage.getProvider());
		customerInfoAllDataEClass.getESuperTypes().add(theProviderPackage.getService());
		customerInfoCurrentStopIndexDataEClass.getESuperTypes().add(theProviderPackage.getService());
		customerInfoCurrentStopPointDataEClass.getESuperTypes().add(theProviderPackage.getService());
		customerInfoTripDataEClass.getESuperTypes().add(theProviderPackage.getService());
		customerInfoVehicleDataEClass.getESuperTypes().add(theProviderPackage.getService());
		customerInfoCurrentAnnouncementDataEClass.getESuperTypes().add(theProviderPackage.getService());
		customerInfoCurrentConnectionDataEClass.getESuperTypes().add(theProviderPackage.getService());
		customerInfoCurrentDisplayContentDataEClass.getESuperTypes().add(theProviderPackage.getService());
		ibisAdminEClass.getESuperTypes().add(theProviderPackage.getAdmin());

		// Initialize classes, features, and operations; add parameters
		initEClass(ibisDeviceEClass, IbisDevice.class, "IbisDevice", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getIbisDevice_CustomerInfoAllData(), this.getCustomerInfoAllData(), null, "customerInfoAllData", null, 0, 1, IbisDevice.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIbisDevice_CustomerInfoCurrentStopIndexData(), this.getCustomerInfoCurrentStopIndexData(), null, "customerInfoCurrentStopIndexData", null, 0, 1, IbisDevice.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIbisDevice_CustomerInfoCurrentStopPointData(), this.getCustomerInfoCurrentStopPointData(), null, "customerInfoCurrentStopPointData", null, 0, 1, IbisDevice.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIbisDevice_CustomerInfoTripData(), this.getCustomerInfoTripData(), null, "customerInfoTripData", null, 0, 1, IbisDevice.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIbisDevice_CustomerInfoVehicleData(), this.getCustomerInfoVehicleData(), null, "customerInfoVehicleData", null, 0, 1, IbisDevice.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIbisDevice_CustomerInfoCurrentAnnouncementData(), this.getCustomerInfoCurrentAnnouncementData(), null, "customerInfoCurrentAnnouncementData", null, 0, 1, IbisDevice.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIbisDevice_CustomerInfoCurrentConnectionData(), this.getCustomerInfoCurrentConnectionData(), null, "customerInfoCurrentConnectionData", null, 0, 1, IbisDevice.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIbisDevice_CustomerInfoCurrentDisplayContentData(), this.getCustomerInfoCurrentDisplayContentData(), null, "customerInfoCurrentDisplayContentData", null, 0, 1, IbisDevice.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(customerInfoAllDataEClass, CustomerInfoAllData.class, "CustomerInfoAllData", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCustomerInfoAllData_ServiceName(), ecorePackage.getEString(), "serviceName", null, 1, 1, CustomerInfoAllData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoAllData_ServiceOperation(), ecorePackage.getEString(), "serviceOperation", null, 1, 1, CustomerInfoAllData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoAllData_Timestamp(), theProviderPackage.getEInstant(), "timestamp", null, 1, 1, CustomerInfoAllData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoAllData_VehicleRef(), ecorePackage.getEString(), "vehicleRef", null, 1, 1, CustomerInfoAllData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoAllData_DefaultLanguage(), ecorePackage.getEString(), "defaultLanguage", null, 1, 1, CustomerInfoAllData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoAllData_CurrentStopIndex(), ecorePackage.getEInt(), "currentStopIndex", "-1", 1, 1, CustomerInfoAllData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoAllData_RouteDeviation(), ecorePackage.getEString(), "routeDeviation", null, 1, 1, CustomerInfoAllData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoAllData_DoorState(), ecorePackage.getEString(), "doorState", null, 0, 1, CustomerInfoAllData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoAllData_InPanic(), ecorePackage.getEBoolean(), "inPanic", null, 0, 1, CustomerInfoAllData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoAllData_VehicleStopRequested(), ecorePackage.getEBoolean(), "vehicleStopRequested", null, 0, 1, CustomerInfoAllData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoAllData_ExitSide(), ecorePackage.getEString(), "exitSide", null, 0, 1, CustomerInfoAllData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoAllData_MovingDirectionForward(), ecorePackage.getEBoolean(), "movingDirectionForward", null, 0, 1, CustomerInfoAllData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoAllData_VehicleMode(), ecorePackage.getEString(), "vehicleMode", null, 0, 1, CustomerInfoAllData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoAllData_SpeakerActive(), ecorePackage.getEBoolean(), "speakerActive", null, 0, 1, CustomerInfoAllData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoAllData_StopInformationActive(), ecorePackage.getEBoolean(), "stopInformationActive", null, 0, 1, CustomerInfoAllData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoAllData_TripState(), ecorePackage.getEString(), "tripState", null, 0, 1, CustomerInfoAllData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(customerInfoCurrentStopIndexDataEClass, CustomerInfoCurrentStopIndexData.class, "CustomerInfoCurrentStopIndexData", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCustomerInfoCurrentStopIndexData_ServiceName(), ecorePackage.getEString(), "serviceName", null, 1, 1, CustomerInfoCurrentStopIndexData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoCurrentStopIndexData_ServiceOperation(), ecorePackage.getEString(), "serviceOperation", null, 1, 1, CustomerInfoCurrentStopIndexData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoCurrentStopIndexData_Timestamp(), theProviderPackage.getEInstant(), "timestamp", null, 1, 1, CustomerInfoCurrentStopIndexData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoCurrentStopIndexData_CurrentStopIndex(), ecorePackage.getEInt(), "currentStopIndex", "-1", 1, 1, CustomerInfoCurrentStopIndexData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(customerInfoCurrentStopPointDataEClass, CustomerInfoCurrentStopPointData.class, "CustomerInfoCurrentStopPointData", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCustomerInfoCurrentStopPointData_ServiceName(), ecorePackage.getEString(), "serviceName", null, 1, 1, CustomerInfoCurrentStopPointData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoCurrentStopPointData_ServiceOperation(), ecorePackage.getEString(), "serviceOperation", null, 1, 1, CustomerInfoCurrentStopPointData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoCurrentStopPointData_Timestamp(), theProviderPackage.getEInstant(), "timestamp", null, 1, 1, CustomerInfoCurrentStopPointData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoCurrentStopPointData_StopIndex(), ecorePackage.getEInt(), "stopIndex", "-1", 1, 1, CustomerInfoCurrentStopPointData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoCurrentStopPointData_StopRef(), ecorePackage.getEString(), "stopRef", null, 1, 1, CustomerInfoCurrentStopPointData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoCurrentStopPointData_StopName(), ecorePackage.getEString(), "stopName", null, 1, -1, CustomerInfoCurrentStopPointData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoCurrentStopPointData_StopAlternativeName(), ecorePackage.getEString(), "stopAlternativeName", null, 0, -1, CustomerInfoCurrentStopPointData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoCurrentStopPointData_Platform(), ecorePackage.getEString(), "platform", null, 0, 1, CustomerInfoCurrentStopPointData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoCurrentStopPointData_ArrivalScheduled(), theProviderPackage.getEInstant(), "arrivalScheduled", null, 0, 1, CustomerInfoCurrentStopPointData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoCurrentStopPointData_ArrivalExpected(), theProviderPackage.getEInstant(), "arrivalExpected", null, 0, 1, CustomerInfoCurrentStopPointData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoCurrentStopPointData_DepartureScheduled(), theProviderPackage.getEInstant(), "departureScheduled", null, 0, 1, CustomerInfoCurrentStopPointData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoCurrentStopPointData_DepartureExpected(), theProviderPackage.getEInstant(), "departureExpected", null, 0, 1, CustomerInfoCurrentStopPointData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoCurrentStopPointData_RecordedArrivalTime(), theProviderPackage.getEInstant(), "recordedArrivalTime", null, 0, 1, CustomerInfoCurrentStopPointData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoCurrentStopPointData_DistanceToNextStop(), ecorePackage.getEInt(), "distanceToNextStop", "-1", 0, 1, CustomerInfoCurrentStopPointData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoCurrentStopPointData_FareZone(), ecorePackage.getEString(), "fareZone", null, 0, -1, CustomerInfoCurrentStopPointData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(customerInfoTripDataEClass, CustomerInfoTripData.class, "CustomerInfoTripData", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCustomerInfoTripData_ServiceName(), ecorePackage.getEString(), "serviceName", null, 1, 1, CustomerInfoTripData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoTripData_ServiceOperation(), ecorePackage.getEString(), "serviceOperation", null, 1, 1, CustomerInfoTripData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoTripData_Timestamp(), theProviderPackage.getEInstant(), "timestamp", null, 1, 1, CustomerInfoTripData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoTripData_VehicleRef(), ecorePackage.getEString(), "vehicleRef", null, 1, 1, CustomerInfoTripData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoTripData_DefaultLanguage(), ecorePackage.getEString(), "defaultLanguage", null, 1, 1, CustomerInfoTripData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoTripData_CurrentStopIndex(), ecorePackage.getEInt(), "currentStopIndex", "-1", 1, 1, CustomerInfoTripData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoTripData_TripRef(), ecorePackage.getEString(), "tripRef", null, 1, 1, CustomerInfoTripData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoTripData_LocationState(), ecorePackage.getEString(), "locationState", null, 0, 1, CustomerInfoTripData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoTripData_TimetableDelay(), ecorePackage.getEInt(), "timetableDelay", null, 0, 1, CustomerInfoTripData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoTripData_RouteDirection(), ecorePackage.getEString(), "routeDirection", null, 0, 1, CustomerInfoTripData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoTripData_RunNumber(), ecorePackage.getEInt(), "runNumber", "-1", 0, 1, CustomerInfoTripData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoTripData_PatternNumber(), ecorePackage.getEInt(), "patternNumber", "-1", 0, 1, CustomerInfoTripData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoTripData_PathDestinationNumber(), ecorePackage.getEInt(), "pathDestinationNumber", "-1", 0, 1, CustomerInfoTripData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoTripData_AdditionalTextMsg(), ecorePackage.getEString(), "additionalTextMsg", "", 0, 1, CustomerInfoTripData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoTripData_AdditionalTextMsg1(), ecorePackage.getEString(), "additionalTextMsg1", null, 0, 1, CustomerInfoTripData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoTripData_AdditionalTextMsg2(), ecorePackage.getEString(), "additionalTextMsg2", null, 0, 1, CustomerInfoTripData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoTripData_AdditionalTextMsg3(), ecorePackage.getEString(), "additionalTextMsg3", null, 0, 1, CustomerInfoTripData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoTripData_AdditionalTextMsg4(), ecorePackage.getEString(), "additionalTextMsg4", null, 0, 1, CustomerInfoTripData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoTripData_AdditionalTextMsg5(), ecorePackage.getEString(), "additionalTextMsg5", null, 0, 1, CustomerInfoTripData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoTripData_AdditionalTextMsg6(), ecorePackage.getEString(), "additionalTextMsg6", null, 0, 1, CustomerInfoTripData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoTripData_AdditionalTextMsg7(), ecorePackage.getEString(), "additionalTextMsg7", null, 0, 1, CustomerInfoTripData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoTripData_AdditionalTextMsg8(), ecorePackage.getEString(), "additionalTextMsg8", null, 0, 1, CustomerInfoTripData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoTripData_AdditionalTextMsg9(), ecorePackage.getEString(), "additionalTextMsg9", null, 0, 1, CustomerInfoTripData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(customerInfoVehicleDataEClass, CustomerInfoVehicleData.class, "CustomerInfoVehicleData", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCustomerInfoVehicleData_ServiceName(), ecorePackage.getEString(), "serviceName", null, 1, 1, CustomerInfoVehicleData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoVehicleData_ServiceOperation(), ecorePackage.getEString(), "serviceOperation", null, 1, 1, CustomerInfoVehicleData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoVehicleData_Timestamp(), theProviderPackage.getEInstant(), "timestamp", null, 1, 1, CustomerInfoVehicleData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoVehicleData_VehicleRef(), ecorePackage.getEString(), "vehicleRef", null, 1, 1, CustomerInfoVehicleData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoVehicleData_RouteDeviation(), ecorePackage.getEString(), "routeDeviation", null, 1, 1, CustomerInfoVehicleData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoVehicleData_DoorState(), ecorePackage.getEString(), "doorState", null, 0, 1, CustomerInfoVehicleData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoVehicleData_InPanic(), ecorePackage.getEBoolean(), "inPanic", null, 0, 1, CustomerInfoVehicleData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoVehicleData_VehicleStopRequested(), ecorePackage.getEBoolean(), "vehicleStopRequested", null, 0, 1, CustomerInfoVehicleData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoVehicleData_ExitSide(), ecorePackage.getEString(), "exitSide", null, 0, 1, CustomerInfoVehicleData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoVehicleData_MovingDirectionForward(), ecorePackage.getEBoolean(), "movingDirectionForward", null, 0, 1, CustomerInfoVehicleData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoVehicleData_VehicleMode(), ecorePackage.getEString(), "vehicleMode", null, 0, 1, CustomerInfoVehicleData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoVehicleData_SpeakerActive(), ecorePackage.getEBoolean(), "speakerActive", null, 0, 1, CustomerInfoVehicleData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoVehicleData_StopInformationActive(), ecorePackage.getEBoolean(), "stopInformationActive", null, 0, 1, CustomerInfoVehicleData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoVehicleData_TripState(), ecorePackage.getEString(), "tripState", null, 0, 1, CustomerInfoVehicleData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(customerInfoCurrentAnnouncementDataEClass, CustomerInfoCurrentAnnouncementData.class, "CustomerInfoCurrentAnnouncementData", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCustomerInfoCurrentAnnouncementData_ServiceName(), ecorePackage.getEString(), "serviceName", null, 1, 1, CustomerInfoCurrentAnnouncementData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoCurrentAnnouncementData_ServiceOperation(), ecorePackage.getEString(), "serviceOperation", null, 1, 1, CustomerInfoCurrentAnnouncementData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoCurrentAnnouncementData_Timestamp(), theProviderPackage.getEInstant(), "timestamp", null, 1, 1, CustomerInfoCurrentAnnouncementData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoCurrentAnnouncementData_AnnouncementRef(), ecorePackage.getEString(), "announcementRef", null, 1, 1, CustomerInfoCurrentAnnouncementData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoCurrentAnnouncementData_AnnouncementText(), ecorePackage.getEString(), "announcementText", null, 0, -1, CustomerInfoCurrentAnnouncementData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoCurrentAnnouncementData_AnnouncementTTSText(), ecorePackage.getEString(), "announcementTTSText", null, 0, -1, CustomerInfoCurrentAnnouncementData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(customerInfoCurrentConnectionDataEClass, CustomerInfoCurrentConnectionData.class, "CustomerInfoCurrentConnectionData", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCustomerInfoCurrentConnectionData_ServiceName(), ecorePackage.getEString(), "serviceName", null, 1, 1, CustomerInfoCurrentConnectionData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoCurrentConnectionData_ServiceOperation(), ecorePackage.getEString(), "serviceOperation", null, 1, 1, CustomerInfoCurrentConnectionData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoCurrentConnectionData_Timestamp(), theProviderPackage.getEInstant(), "timestamp", null, 1, 1, CustomerInfoCurrentConnectionData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(customerInfoCurrentDisplayContentDataEClass, CustomerInfoCurrentDisplayContentData.class, "CustomerInfoCurrentDisplayContentData", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCustomerInfoCurrentDisplayContentData_ServiceName(), ecorePackage.getEString(), "serviceName", null, 1, 1, CustomerInfoCurrentDisplayContentData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoCurrentDisplayContentData_ServiceOperation(), ecorePackage.getEString(), "serviceOperation", null, 1, 1, CustomerInfoCurrentDisplayContentData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomerInfoCurrentDisplayContentData_Timestamp(), theProviderPackage.getEInstant(), "timestamp", null, 1, 1, CustomerInfoCurrentDisplayContentData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(ibisAdminEClass, IbisAdmin.class, "IbisAdmin", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIbisAdmin_DeviceType(), ecorePackage.getEString(), "deviceType", null, 1, 1, IbisAdmin.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http://www.eclipse.org/OCL/Import
		createImportAnnotations();
		// http://www.eclipse.org/emf/2002/GenModel
		createGenModelAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http://www.eclipse.org/OCL/Import</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createImportAnnotations() {
		String source = "http://www.eclipse.org/OCL/Import";
		addAnnotation
		  (this,
		   source,
		   new String[] {
			   "ecore", "http://www.eclipse.org/emf/2002/Ecore"
		   });
	}

	/**
	 * Initializes the annotations for <b>http://www.eclipse.org/emf/2002/GenModel</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createGenModelAnnotations() {
		String source = "http://www.eclipse.org/emf/2002/GenModel";
		addAnnotation
		  (ibisDeviceEClass,
		   source,
		   new String[] {
			   "documentation", "This represents the public transport device, meaning the bus, tram, or whatever."
		   });
	}

} //IbisSensinactPackageImpl
