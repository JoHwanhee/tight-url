package ga.tight.shortenurl.gloabl.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;

class DtoMapper extends ModelMapper {
    public DtoMapper(){
        getConfiguration().setSkipNullEnabled(true);
        getConfiguration().setFieldMatchingEnabled(true);
        getConfiguration().setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
        getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
    }
}


class Test {

}