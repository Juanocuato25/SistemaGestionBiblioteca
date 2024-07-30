package com.jarenas.msvc.book.mapper;

import com.jarenas.msvc.book.dto.BookCreationDTO;
import com.jarenas.msvc.book.dto.BookDTO;
import com.jarenas.msvc.book.model.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    Book toBook(BookCreationDTO bookCreationDTO);

    BookDTO toBookDTO(Book book);
}
