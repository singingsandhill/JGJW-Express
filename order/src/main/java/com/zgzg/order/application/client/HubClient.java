package com.zgzg.order.application.client;

import java.util.UUID;

import com.zgzg.order.infrastructure.dto.HubResponseDTO;

public interface HubClient {
	HubResponseDTO getHub(UUID receiverHubId);
}
