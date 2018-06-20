import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import com.oath.common.snakewars.settings.GameBoard;
import com.oath.common.snakewars.settings.GameUpdate;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class SerdeTest
{
  public static void main (String args[]) throws IOException
  {
    //GameBoardTest g = new GameBoardTest(4,4);
    GameBoard g = new GameBoard(4,4);

    //GameUpdate gu = new GameUpdate(1,g);
    ObjectMapper objectMapper = new ObjectMapper();
    //GameUpdateTest gut = new GameUpdateTest(2,g);
    GameUpdate gut = new GameUpdate(2,g);
     gut.getGameBoard().getTestBoard();
     gut.getGameBoard().setTestboard(3);
    //gut.setTest(100);
    String str = objectMapper.writeValueAsString(gut);
    InputStream targetStream = new ByteArrayInputStream(str.getBytes());
    GameUpdate gudash = objectMapper.readValue(targetStream,GameUpdate.class);
    //GameUpdateTest gudash = objectMapper.readValue(targetStream,GameUpdateTest.class);

    gudash.getGameBoard().getTestBoard();
  }
}
