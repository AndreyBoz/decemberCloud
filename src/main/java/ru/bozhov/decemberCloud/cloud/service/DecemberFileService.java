package ru.bozhov.decemberCloud.cloud.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bozhov.decemberCloud.cloud.repository.DecemberFileRepository;

@Service
@AllArgsConstructor
public class DecemberFileService {
    DecemberFileRepository decemberFileRepository;
}
