package advanced_crud_with_spring_boot.mapper;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

import java.util.ArrayList;
import java.util.List;

public class DozerObjectMapper {
    private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();

    public static <O, D>D parseObject(O origin, Class<D> destination){
        return mapper.map(origin, destination);
    }

    public static <O, D>List<D> parseListOfObject(List<O> origin, Class<D> destination){
        List<D> objects = new ArrayList<>();
        for (Object o: origin){
            objects.add(mapper.map(o, destination));
        }
        return objects;
    }
}

