package gg.dawn.serverapi.bukkit.ui.rpc;

import gg.dawn.serverapi.api.ui.rpc.RpcRequest;
import gg.dawn.serverapi.api.ui.rpc.RpcResponse;

interface RpcHandlerExecutor {
  void invoke(RpcRequest request, RpcResponse response);
}
