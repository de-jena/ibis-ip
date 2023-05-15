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

import org.eclipse.sensinact.model.core.provider.Provider;

import org.osgi.annotation.versioning.ProviderType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ibis Device</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * This represents the public transport device, meaning the bus, tram, or whatever.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.jena.model.sensinact.ibis.IbisDevice#getIbisAdmin <em>Ibis Admin</em>}</li>
 *   <li>{@link de.jena.model.sensinact.ibis.IbisDevice#getCustomerInfoAllData <em>Customer Info All Data</em>}</li>
 *   <li>{@link de.jena.model.sensinact.ibis.IbisDevice#getCustomerInfoCurrentStopIndexData <em>Customer Info Current Stop Index Data</em>}</li>
 *   <li>{@link de.jena.model.sensinact.ibis.IbisDevice#getCustomerInfoCurrentStopPointData <em>Customer Info Current Stop Point Data</em>}</li>
 *   <li>{@link de.jena.model.sensinact.ibis.IbisDevice#getCustomerInfoTripData <em>Customer Info Trip Data</em>}</li>
 *   <li>{@link de.jena.model.sensinact.ibis.IbisDevice#getCustomerInfoVehicleData <em>Customer Info Vehicle Data</em>}</li>
 *   <li>{@link de.jena.model.sensinact.ibis.IbisDevice#getCustomerInfoCurrentAnnouncementData <em>Customer Info Current Announcement Data</em>}</li>
 *   <li>{@link de.jena.model.sensinact.ibis.IbisDevice#getCustomerInfoCurrentConnectionData <em>Customer Info Current Connection Data</em>}</li>
 *   <li>{@link de.jena.model.sensinact.ibis.IbisDevice#getCustomerInfoCurrentDisplayContentData <em>Customer Info Current Display Content Data</em>}</li>
 *   <li>{@link de.jena.model.sensinact.ibis.IbisDevice#getDoorCountingStateData <em>Door Counting State Data</em>}</li>
 *   <li>{@link de.jena.model.sensinact.ibis.IbisDevice#getDoorStateData <em>Door State Data</em>}</li>
 *   <li>{@link de.jena.model.sensinact.ibis.IbisDevice#getStopRequested <em>Stop Requested</em>}</li>
 *   <li>{@link de.jena.model.sensinact.ibis.IbisDevice#getGnssLocationData <em>Gnss Location Data</em>}</li>
 *   <li>{@link de.jena.model.sensinact.ibis.IbisDevice#getCurrentTariffStopData <em>Current Tariff Stop Data</em>}</li>
 *   <li>{@link de.jena.model.sensinact.ibis.IbisDevice#getRazziaData <em>Razzia Data</em>}</li>
 *   <li>{@link de.jena.model.sensinact.ibis.IbisDevice#getCurrentLineData <em>Current Line Data</em>}</li>
 *   <li>{@link de.jena.model.sensinact.ibis.IbisDevice#getVehicleData <em>Vehicle Data</em>}</li>
 * </ul>
 *
 * @see de.jena.model.sensinact.ibis.IbisSensinactPackage#getIbisDevice()
 * @model
 * @generated
 */
@ProviderType
public interface IbisDevice extends Provider {
	/**
	 * Returns the value of the '<em><b>Ibis Admin</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ibis Admin</em>' containment reference.
	 * @see #setIbisAdmin(IbisAdmin)
	 * @see de.jena.model.sensinact.ibis.IbisSensinactPackage#getIbisDevice_IbisAdmin()
	 * @model containment="true"
	 * @generated
	 */
	IbisAdmin getIbisAdmin();

	/**
	 * Sets the value of the '{@link de.jena.model.sensinact.ibis.IbisDevice#getIbisAdmin <em>Ibis Admin</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ibis Admin</em>' containment reference.
	 * @see #getIbisAdmin()
	 * @generated
	 */
	void setIbisAdmin(IbisAdmin value);

	/**
	 * Returns the value of the '<em><b>Customer Info All Data</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Customer Info All Data</em>' containment reference.
	 * @see #setCustomerInfoAllData(CustomerInfoAllData)
	 * @see de.jena.model.sensinact.ibis.IbisSensinactPackage#getIbisDevice_CustomerInfoAllData()
	 * @model containment="true"
	 * @generated
	 */
	CustomerInfoAllData getCustomerInfoAllData();

	/**
	 * Sets the value of the '{@link de.jena.model.sensinact.ibis.IbisDevice#getCustomerInfoAllData <em>Customer Info All Data</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Customer Info All Data</em>' containment reference.
	 * @see #getCustomerInfoAllData()
	 * @generated
	 */
	void setCustomerInfoAllData(CustomerInfoAllData value);

	/**
	 * Returns the value of the '<em><b>Customer Info Current Stop Index Data</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Customer Info Current Stop Index Data</em>' containment reference.
	 * @see #setCustomerInfoCurrentStopIndexData(CustomerInfoCurrentStopIndexData)
	 * @see de.jena.model.sensinact.ibis.IbisSensinactPackage#getIbisDevice_CustomerInfoCurrentStopIndexData()
	 * @model containment="true"
	 * @generated
	 */
	CustomerInfoCurrentStopIndexData getCustomerInfoCurrentStopIndexData();

	/**
	 * Sets the value of the '{@link de.jena.model.sensinact.ibis.IbisDevice#getCustomerInfoCurrentStopIndexData <em>Customer Info Current Stop Index Data</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Customer Info Current Stop Index Data</em>' containment reference.
	 * @see #getCustomerInfoCurrentStopIndexData()
	 * @generated
	 */
	void setCustomerInfoCurrentStopIndexData(CustomerInfoCurrentStopIndexData value);

	/**
	 * Returns the value of the '<em><b>Customer Info Current Stop Point Data</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Customer Info Current Stop Point Data</em>' containment reference.
	 * @see #setCustomerInfoCurrentStopPointData(CustomerInfoCurrentStopPointData)
	 * @see de.jena.model.sensinact.ibis.IbisSensinactPackage#getIbisDevice_CustomerInfoCurrentStopPointData()
	 * @model containment="true"
	 * @generated
	 */
	CustomerInfoCurrentStopPointData getCustomerInfoCurrentStopPointData();

	/**
	 * Sets the value of the '{@link de.jena.model.sensinact.ibis.IbisDevice#getCustomerInfoCurrentStopPointData <em>Customer Info Current Stop Point Data</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Customer Info Current Stop Point Data</em>' containment reference.
	 * @see #getCustomerInfoCurrentStopPointData()
	 * @generated
	 */
	void setCustomerInfoCurrentStopPointData(CustomerInfoCurrentStopPointData value);

	/**
	 * Returns the value of the '<em><b>Customer Info Trip Data</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Customer Info Trip Data</em>' containment reference.
	 * @see #setCustomerInfoTripData(CustomerInfoTripData)
	 * @see de.jena.model.sensinact.ibis.IbisSensinactPackage#getIbisDevice_CustomerInfoTripData()
	 * @model containment="true"
	 * @generated
	 */
	CustomerInfoTripData getCustomerInfoTripData();

	/**
	 * Sets the value of the '{@link de.jena.model.sensinact.ibis.IbisDevice#getCustomerInfoTripData <em>Customer Info Trip Data</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Customer Info Trip Data</em>' containment reference.
	 * @see #getCustomerInfoTripData()
	 * @generated
	 */
	void setCustomerInfoTripData(CustomerInfoTripData value);

	/**
	 * Returns the value of the '<em><b>Customer Info Vehicle Data</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Customer Info Vehicle Data</em>' containment reference.
	 * @see #setCustomerInfoVehicleData(CustomerInfoVehicleData)
	 * @see de.jena.model.sensinact.ibis.IbisSensinactPackage#getIbisDevice_CustomerInfoVehicleData()
	 * @model containment="true"
	 * @generated
	 */
	CustomerInfoVehicleData getCustomerInfoVehicleData();

	/**
	 * Sets the value of the '{@link de.jena.model.sensinact.ibis.IbisDevice#getCustomerInfoVehicleData <em>Customer Info Vehicle Data</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Customer Info Vehicle Data</em>' containment reference.
	 * @see #getCustomerInfoVehicleData()
	 * @generated
	 */
	void setCustomerInfoVehicleData(CustomerInfoVehicleData value);

	/**
	 * Returns the value of the '<em><b>Customer Info Current Announcement Data</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Customer Info Current Announcement Data</em>' containment reference.
	 * @see #setCustomerInfoCurrentAnnouncementData(CustomerInfoCurrentAnnouncementData)
	 * @see de.jena.model.sensinact.ibis.IbisSensinactPackage#getIbisDevice_CustomerInfoCurrentAnnouncementData()
	 * @model containment="true"
	 * @generated
	 */
	CustomerInfoCurrentAnnouncementData getCustomerInfoCurrentAnnouncementData();

	/**
	 * Sets the value of the '{@link de.jena.model.sensinact.ibis.IbisDevice#getCustomerInfoCurrentAnnouncementData <em>Customer Info Current Announcement Data</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Customer Info Current Announcement Data</em>' containment reference.
	 * @see #getCustomerInfoCurrentAnnouncementData()
	 * @generated
	 */
	void setCustomerInfoCurrentAnnouncementData(CustomerInfoCurrentAnnouncementData value);

	/**
	 * Returns the value of the '<em><b>Customer Info Current Connection Data</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Customer Info Current Connection Data</em>' containment reference.
	 * @see #setCustomerInfoCurrentConnectionData(CustomerInfoCurrentConnectionData)
	 * @see de.jena.model.sensinact.ibis.IbisSensinactPackage#getIbisDevice_CustomerInfoCurrentConnectionData()
	 * @model containment="true"
	 * @generated
	 */
	CustomerInfoCurrentConnectionData getCustomerInfoCurrentConnectionData();

	/**
	 * Sets the value of the '{@link de.jena.model.sensinact.ibis.IbisDevice#getCustomerInfoCurrentConnectionData <em>Customer Info Current Connection Data</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Customer Info Current Connection Data</em>' containment reference.
	 * @see #getCustomerInfoCurrentConnectionData()
	 * @generated
	 */
	void setCustomerInfoCurrentConnectionData(CustomerInfoCurrentConnectionData value);

	/**
	 * Returns the value of the '<em><b>Customer Info Current Display Content Data</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Customer Info Current Display Content Data</em>' containment reference.
	 * @see #setCustomerInfoCurrentDisplayContentData(CustomerInfoCurrentDisplayContentData)
	 * @see de.jena.model.sensinact.ibis.IbisSensinactPackage#getIbisDevice_CustomerInfoCurrentDisplayContentData()
	 * @model containment="true"
	 * @generated
	 */
	CustomerInfoCurrentDisplayContentData getCustomerInfoCurrentDisplayContentData();

	/**
	 * Sets the value of the '{@link de.jena.model.sensinact.ibis.IbisDevice#getCustomerInfoCurrentDisplayContentData <em>Customer Info Current Display Content Data</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Customer Info Current Display Content Data</em>' containment reference.
	 * @see #getCustomerInfoCurrentDisplayContentData()
	 * @generated
	 */
	void setCustomerInfoCurrentDisplayContentData(CustomerInfoCurrentDisplayContentData value);

	/**
	 * Returns the value of the '<em><b>Door Counting State Data</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Door Counting State Data</em>' containment reference.
	 * @see #setDoorCountingStateData(PassengerCountingDoorCountingState)
	 * @see de.jena.model.sensinact.ibis.IbisSensinactPackage#getIbisDevice_DoorCountingStateData()
	 * @model containment="true"
	 * @generated
	 */
	PassengerCountingDoorCountingState getDoorCountingStateData();

	/**
	 * Sets the value of the '{@link de.jena.model.sensinact.ibis.IbisDevice#getDoorCountingStateData <em>Door Counting State Data</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Door Counting State Data</em>' containment reference.
	 * @see #getDoorCountingStateData()
	 * @generated
	 */
	void setDoorCountingStateData(PassengerCountingDoorCountingState value);

	/**
	 * Returns the value of the '<em><b>Door State Data</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Door State Data</em>' containment reference.
	 * @see #setDoorStateData(DoorState)
	 * @see de.jena.model.sensinact.ibis.IbisSensinactPackage#getIbisDevice_DoorStateData()
	 * @model containment="true"
	 * @generated
	 */
	DoorState getDoorStateData();

	/**
	 * Sets the value of the '{@link de.jena.model.sensinact.ibis.IbisDevice#getDoorStateData <em>Door State Data</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Door State Data</em>' containment reference.
	 * @see #getDoorStateData()
	 * @generated
	 */
	void setDoorStateData(DoorState value);

	/**
	 * Returns the value of the '<em><b>Stop Requested</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Stop Requested</em>' containment reference.
	 * @see #setStopRequested(StopRequested)
	 * @see de.jena.model.sensinact.ibis.IbisSensinactPackage#getIbisDevice_StopRequested()
	 * @model containment="true"
	 * @generated
	 */
	StopRequested getStopRequested();

	/**
	 * Sets the value of the '{@link de.jena.model.sensinact.ibis.IbisDevice#getStopRequested <em>Stop Requested</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Stop Requested</em>' containment reference.
	 * @see #getStopRequested()
	 * @generated
	 */
	void setStopRequested(StopRequested value);

	/**
	 * Returns the value of the '<em><b>Gnss Location Data</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Gnss Location Data</em>' containment reference.
	 * @see #setGnssLocationData(GNSSLocationData)
	 * @see de.jena.model.sensinact.ibis.IbisSensinactPackage#getIbisDevice_GnssLocationData()
	 * @model containment="true"
	 * @generated
	 */
	GNSSLocationData getGnssLocationData();

	/**
	 * Sets the value of the '{@link de.jena.model.sensinact.ibis.IbisDevice#getGnssLocationData <em>Gnss Location Data</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Gnss Location Data</em>' containment reference.
	 * @see #getGnssLocationData()
	 * @generated
	 */
	void setGnssLocationData(GNSSLocationData value);

	/**
	 * Returns the value of the '<em><b>Current Tariff Stop Data</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Current Tariff Stop Data</em>' containment reference.
	 * @see #setCurrentTariffStopData(TicketValidationCurrentTariffStopData)
	 * @see de.jena.model.sensinact.ibis.IbisSensinactPackage#getIbisDevice_CurrentTariffStopData()
	 * @model containment="true"
	 * @generated
	 */
	TicketValidationCurrentTariffStopData getCurrentTariffStopData();

	/**
	 * Sets the value of the '{@link de.jena.model.sensinact.ibis.IbisDevice#getCurrentTariffStopData <em>Current Tariff Stop Data</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Current Tariff Stop Data</em>' containment reference.
	 * @see #getCurrentTariffStopData()
	 * @generated
	 */
	void setCurrentTariffStopData(TicketValidationCurrentTariffStopData value);

	/**
	 * Returns the value of the '<em><b>Razzia Data</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Razzia Data</em>' containment reference.
	 * @see #setRazziaData(TicketValidationRazziaData)
	 * @see de.jena.model.sensinact.ibis.IbisSensinactPackage#getIbisDevice_RazziaData()
	 * @model containment="true"
	 * @generated
	 */
	TicketValidationRazziaData getRazziaData();

	/**
	 * Sets the value of the '{@link de.jena.model.sensinact.ibis.IbisDevice#getRazziaData <em>Razzia Data</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Razzia Data</em>' containment reference.
	 * @see #getRazziaData()
	 * @generated
	 */
	void setRazziaData(TicketValidationRazziaData value);

	/**
	 * Returns the value of the '<em><b>Current Line Data</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Current Line Data</em>' containment reference.
	 * @see #setCurrentLineData(TicketValidationCurrentLineData)
	 * @see de.jena.model.sensinact.ibis.IbisSensinactPackage#getIbisDevice_CurrentLineData()
	 * @model containment="true"
	 * @generated
	 */
	TicketValidationCurrentLineData getCurrentLineData();

	/**
	 * Sets the value of the '{@link de.jena.model.sensinact.ibis.IbisDevice#getCurrentLineData <em>Current Line Data</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Current Line Data</em>' containment reference.
	 * @see #getCurrentLineData()
	 * @generated
	 */
	void setCurrentLineData(TicketValidationCurrentLineData value);

	/**
	 * Returns the value of the '<em><b>Vehicle Data</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Vehicle Data</em>' containment reference.
	 * @see #setVehicleData(TicketValidationVehicleData)
	 * @see de.jena.model.sensinact.ibis.IbisSensinactPackage#getIbisDevice_VehicleData()
	 * @model containment="true"
	 * @generated
	 */
	TicketValidationVehicleData getVehicleData();

	/**
	 * Sets the value of the '{@link de.jena.model.sensinact.ibis.IbisDevice#getVehicleData <em>Vehicle Data</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Vehicle Data</em>' containment reference.
	 * @see #getVehicleData()
	 * @generated
	 */
	void setVehicleData(TicketValidationVehicleData value);

} // IbisDevice
