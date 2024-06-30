package org.kbalazsworks.room_occupancy_manager.api.value_objects;

public record ResponseData<T>(
    T data,
    Boolean success,
    int errorCode,
    String requestId
)
{
}
