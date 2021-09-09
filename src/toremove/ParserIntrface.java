package toremove;

import java.util.List;

public interface ParserIntrface<OutputType, InputType> {
	public List<OutputType> parse(InputType data);
}
