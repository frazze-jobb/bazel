// Copyright 2020 The Bazel Authors. All rights reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package com.google.devtools.build.lib.remote.common;

import build.bazel.remote.execution.v2.RequestMetadata;
import com.google.devtools.build.lib.actions.Spawn;
import javax.annotation.Nullable;

/** A context that provide remote execution related information for executing an action remotely. */
public interface RemoteActionExecutionContext {

  /** Returns the {@link Spawn} of the action being executed or {@code null}. */
  @Nullable
  Spawn getSpawn();

  /** Returns the {@link RequestMetadata} for the action being executed. */
  RequestMetadata getRequestMetadata();

  /**
   * Returns the {@link NetworkTime} instance used to measure the network time during the action
   * execution.
   */
  NetworkTime getNetworkTime();

  /** Creates a {@link SimpleRemoteActionExecutionContext} with given {@link RequestMetadata}. */
  static RemoteActionExecutionContext create(RequestMetadata metadata) {
    return new SimpleRemoteActionExecutionContext(/*spawn=*/ null, metadata, new NetworkTime());
  }

  /**
   * Creates a {@link SimpleRemoteActionExecutionContext} with given {@link Spawn} and {@link
   * RequestMetadata}.
   */
  static RemoteActionExecutionContext createForSpawn(Spawn spawn, RequestMetadata metadata) {
    return new SimpleRemoteActionExecutionContext(spawn, metadata, new NetworkTime());
  }
}
