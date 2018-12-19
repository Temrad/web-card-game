package ua.od.game.service;

import ua.od.game.dto.MessageDto;

import java.util.Date;
import java.util.List;

public interface MessageService {
    List<MessageDto> getMessageList(Integer fromAccountId, Integer toAccountId, Date fromTime);
    Boolean sendMessage(MessageDto message);
}
