package com.mateuszroszkowski.ServerManager.service;

import com.mateuszroszkowski.ServerManager.enumeration.Status;
import com.mateuszroszkowski.ServerManager.model.Server;
import com.mateuszroszkowski.ServerManager.repository.ServerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collection;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ServerServiceImpl implements ServerService {

    private String serverImg = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTuEe49YU2lpWqkNMQQAa8ZqWaBFHF2Rd7Llg&usqp=CAU";
    private final ServerRepository serverRepository;

    @Override
    public Server ping(String ipAddress) throws IOException {
        log.info("Pinging server Ip: " + ipAddress);
        Server server = serverRepository.findByIpAddress(ipAddress);
        InetAddress address = InetAddress.getByName(ipAddress);
        server.setStatus(address.isReachable(10000) ? Status.SERVER_UP : Status.SERVER_DOWN);
        return serverRepository.save(server);
    }

    @Override
    public Server create(Server server) {
        log.info("saving server: " + server.getName());
        server.setImageUrl(setServerImageUrl());
        return serverRepository.save(server);
    }


    @Override
    public Collection<Server> list(int limit) {
        log.info("Fetching all servers");
        return serverRepository.findAll(PageRequest.of(0,limit)).toList();
    }

    @Override
    public Server get(Long id) {
        log.info("Fetching sever by id: " + id);
        return serverRepository.findById(id).get();
    }

    @Override
    public Server update(Server server) {
        log.info("Updating server: " + server.getName());
        return serverRepository.save(server);
    }

    @Override
    public Boolean delete(Long id) {
        log.info("Deleting server by id: " + id);
        serverRepository.deleteById(id);
        return true;
    }

    private String setServerImageUrl() {
        return serverImg;
    }
}
