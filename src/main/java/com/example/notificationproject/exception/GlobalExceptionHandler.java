package com.example.notificationproject.exception;

import com.example.notificationproject.Model.dto.respond.ApiErrorRespondDTO;
import com.example.notificationproject.Model.entity.Log;
import com.example.notificationproject.mapper.ApiErrorMapper;
import com.example.notificationproject.service.database.LogService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final LogService logService;
    private final ObjectMapper objectMapper;
    private final ApiErrorMapper apiErrorMapper = Mappers.getMapper(ApiErrorMapper.class);

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<ApiErrorRespondDTO> handleEnumConversionException(InvalidFormatException ex) {
        return ResponseEntity.ok(apiErrorMapper.ExceptionToErrorDTO(ex));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiErrorRespondDTO> handleNotFoundException(NotFoundException ex) {
        return ResponseEntity.status(ex.getHttpStatus()).body(apiErrorMapper.ExceptionToErrorDTO(ex));
    }

    @ExceptionHandler(MongoDuplicateIndexException.class)
    public ResponseEntity<ApiErrorRespondDTO> handleMongoDuplicateIndexException(MongoDuplicateIndexException ex) {
        return ResponseEntity.status(ex.getHttpStatus()).body(apiErrorMapper.ExceptionToErrorDTO(ex));
    }

    @ExceptionHandler(MessageException.class)
    public ResponseEntity<ApiErrorRespondDTO> handleMessageException(MessageException ex) {
        ApiErrorRespondDTO apiErrorRespondDTO = apiErrorMapper.ExceptionToErrorDTO(ex);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String json;
        try {
            json = objectMapper.writeValueAsString(apiErrorRespondDTO);
        } catch (Exception e) {
            json = "Yanıt Stringe Dönüştürülemedi";
        }
        logService.saveLogAsync(new Log(request,json)); // burda kaydettim;
        return ResponseEntity.status(ex.getHttpStatus()).body(apiErrorRespondDTO);
    }

    @ExceptionHandler(DataBaseException.class)
    public ResponseEntity<ApiErrorRespondDTO> handleDataBaseException(DataBaseException ex) {
        return ResponseEntity.status(ex.getHttpStatus()).body(apiErrorMapper.ExceptionToErrorDTO(ex));
    }



} 