/*
 * Copyright 2016-2018 shardingsphere.io.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package io.shardingsphere.core.executor.event;

import com.google.common.base.Optional;
import lombok.AccessLevel;
import lombok.Getter;

import java.util.UUID;

/**
 * Execution event.
 *
 * @author zhangliang
 */
@Getter
public abstract class ExecutionEvent {
    
    private final String id = UUID.randomUUID().toString();
    
    private EventExecutionType eventExecutionType = EventExecutionType.BEFORE_EXECUTE;
    
    @Getter(AccessLevel.NONE)
    private Exception exception;
    
    /**
     * Set execute success.
     */
    public void setExecuteSuccess() {
        eventExecutionType = EventExecutionType.EXECUTE_SUCCESS;
    }
    
    /**
     * Set execute failure.
     * 
     * @param cause fail cause
     */
    public void setExecuteFailure(final Exception cause) {
        eventExecutionType = EventExecutionType.EXECUTE_FAILURE;
        exception = cause;
    }
    
    /**
     * Get exception.
     * 
     * @return exception
     */
    public final Optional<? extends Exception> getException() {
        return Optional.fromNullable(exception);
    }
}
