package com.zgzg.delivery.application.client;

import java.util.UUID;

import com.zgzg.delivery.infrastructure.client.res.DeliveryUserResponseDTO;

public interface DeliveryPersonClient {
	DeliveryUserResponseDTO getHubDeiveryPerson();

	DeliveryUserResponseDTO getStoreDeiveryPerson(UUID endHubId);
}