package com.zgzg.hub.application.hub.res;

import com.zgzg.hub.domain.entity.Hub;
import java.io.Serializable;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HubResDTO implements Serializable {

  private HubDTO hubDTO;

  public static HubResDTO from(Hub hub) {
    return HubResDTO.builder()
        .hubDTO(HubDTO.from(hub))
        .build();
  }

  @Getter
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class HubDTO implements Serializable {

    private UUID hubId;
    private String hubName;
    private String hubAddress;
    private String hubLatitude;
    private String hubLongitude;
    private Long hubAdminId;

    private boolean megaHubStatus;
    private UUID parentHubId;

    public static HubDTO from(Hub hub) {
      return HubDTO.builder()
          .hubId(hub.getHubId())
          .hubName(hub.getHubName())
          .hubAddress(hub.getHubAddress())
          .hubLatitude(hub.getHubLatitude())
          .hubLongitude(hub.getHubLongitude())
          .hubAdminId(hub.getHubAdminId())
          .megaHubStatus(hub.isMegaHubStatus())
          .parentHubId(hub.getParentHubId())
          .build();
    }
  }
}
