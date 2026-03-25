package com.stellantis.operix.service.importsap;

import com.stellantis.operix.dto.importsap.ImportResultDto;
import org.jspecify.annotations.Nullable;

import java.io.InputStream;

public interface ImportService {
    ImportResultDto importer(InputStream inputStream, @Nullable String originalFilename);
}
