package com.rmuti.guidemap.backend.repository;

import com.rmuti.guidemap.backend.table.ChatMessage;
import com.rmuti.guidemap.backend.table.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, String> {

    List<ChatRoom> findByLocationId(String locationId);

    Optional<ChatRoom> findByCrName(String name);

    boolean existsByCrName(String name);

}
