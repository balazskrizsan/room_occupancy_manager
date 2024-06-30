FROM ubuntu:jammy

COPY target/room_occupancy_manager /room_occupancy_manager

CMD ["/room_occupancy_manager"]
