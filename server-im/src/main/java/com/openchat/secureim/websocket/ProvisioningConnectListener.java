package com.openchat.secureim.websocket;

import com.openchat.secureim.storage.PubSubManager;
import com.openchat.websocket.session.WebSocketSessionContext;
import com.openchat.websocket.setup.WebSocketConnectListener;

public class ProvisioningConnectListener implements WebSocketConnectListener {

  private final PubSubManager pubSubManager;

  public ProvisioningConnectListener(PubSubManager pubSubManager) {
    this.pubSubManager = pubSubManager;
  }

  @Override
  public void onWebSocketConnect(WebSocketSessionContext context) {
    final ProvisioningConnection connection          = new ProvisioningConnection(context.getClient());
    final ProvisioningAddress    provisioningAddress = ProvisioningAddress.generate();

    pubSubManager.subscribe(provisioningAddress, connection);

    context.addListener(new WebSocketSessionContext.WebSocketEventListener() {
      @Override
      public void onWebSocketClose(WebSocketSessionContext context, int statusCode, String reason) {
        pubSubManager.unsubscribe(provisioningAddress, connection);
      }
    });
  }
}
