package com.easyride.easyRideApp.config;

import com.easyride.easyRideApp.dto.PointDto;
import com.easyride.easyRideApp.utils.GeometryUtil;
import org.locationtech.jts.geom.Point;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper mapper =  new ModelMapper();
        mapper.typeMap(PointDto.class, Point.class).setConverter(converter -> {
            PointDto pointDto = converter.getSource();
            return GeometryUtil.createPoint(pointDto);
        });

        mapper.typeMap(Point.class,PointDto.class).setConverter(converter ->{
            Point point = converter.getSource();
            double coordinates[] = {point.getX(),point.getY()};
            return new PointDto(coordinates);
        });

        return mapper;
    }
}
