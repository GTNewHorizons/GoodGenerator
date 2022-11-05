package goodgenerator.util.cyclotron;

import static com.gtnewhorizon.structurelib.structure.StructureUtility.transpose;

@SuppressWarnings("SpellCheckingInspection")
public abstract class CYCLOTRON_SHAPE_FILE_2 {
    public static final String[][] CYCLOTRON_SHAPE_2_IDENTITY = transpose(new String[][] {
        {
            "                                                       ",
            "                      BBB     BBB                      ",
            "                   BBBBBB     BBBBBB                   ",
            "                BBBBBBBBB     BBBBBBBBB                ",
            "              BBBBBBBBB         BBBBBBBBB              ",
            "             BBBBBB                 BBBBBB             ",
            "           BBBBBB                     BBBBBB           ",
            "          BBBBB                         BBBBB          ",
            "         BBBBB                           BBBBB         ",
            "        BBBBB                             BBBBB        ",
            "       BBBB                                 BBBB       ",
            "      BBBB                                   BBBB      ",
            "      BBBB                                   BBBB      ",
            "     BBBB                                     BBBB     ",
            "    BBBB                                       BBBB    ",
            "    BBB                                         BBB    ",
            "   BBBB                                         BBBB   ",
            "   BBB                                           BBB   ",
            "   BBB                                           BBB   ",
            "  BBB                                             BBB  ",
            "  BBB                                             BBB  ",
            "  BBB                                             BBB  ",
            " BBBB                                             BBBB ",
            " BBB                                               BBB ",
            " BBB                                               BBB ",
            " BBB                                               BBB ",
            " BBB                                               BBB ",
            " BBB                                               BBB ",
            " BBB                                               BBB ",
            " BBB                                               BBB ",
            " BBB                                               BBB ",
            " BBB                                               BBB ",
            " BBBB                                             BBBB ",
            "  BBB                                             BBB  ",
            "  BBB                                             BBB  ",
            "  BBB                                             BBB  ",
            "   BBB                                           BBB   ",
            "   BBB                                           BBB   ",
            "   BBBB                                         BBBB   ",
            "    BBB                                         BBB    ",
            "    BBBB                                       BBBB    ",
            "     BBBB                                     BBBB     ",
            "      BBBB                                   BBBB      ",
            "      BBBB                                   BBBB      ",
            "       BBBB                                 BBBB       ",
            "        BBBBB                             BBBBB        ",
            "         BBBBB                           BBBBB         ",
            "          BBBBB                         BBBBB          ",
            "           BBBBBB                     BBBBBB           ",
            "             BBBBBB                 BBBBBB             ",
            "              BBBBBBBBB         BBBBBBBBB              ",
            "                BBBBBBBBBBBBBBBBBBBBBBB                ",
            "                   BBBBBBBBBBBBBBBBB                   ",
            "                      BBBBBBBBBBB                      ",
            "                                                       "
        },
        {
            "                      BBB     BBB                      ",
            "                   BBBWWW     WWWBBB                   ",
            "                BBBWWWWWW     WWWWWWBBB                ",
            "              BBWWWWWWWWW     WWWWWWWWWBB              ",
            "             BWWWWWWWWWBB     BBWWWWWWWWWB             ",
            "           BBWWWWWWBBBB         BBBBWWWWWWBB           ",
            "          BWWWWWWBB                 BBWWWWWWB          ",
            "         BWWWWWBB                     BBWWWWWB         ",
            "        BWWWWWB                         BWWWWWB        ",
            "       BWWWWWB                           BWWWWWB       ",
            "      BWWWWBB                             BBWWWWB      ",
            "     BWWWWB                                 BWWWWB     ",
            "     BWWWWB                                 BWWWWB     ",
            "    BWWWWB                                   BWWWWB    ",
            "   BWWWWB                                     BWWWWB   ",
            "   BWWWB                                       BWWWB   ",
            "  BWWWWB                                       BWWWWB  ",
            "  BWWWB                                         BWWWB  ",
            "  BWWWB                                         BWWWB  ",
            " BWWWB                                           BWWWB ",
            " BWWWB                                           BWWWB ",
            " BWWWB                                           BWWWB ",
            "BWWWWB                                           BWWWWB",
            "BWWWB                                             BWWWB",
            "BWWWB                                             BWWWB",
            "BWWWB                                             BWWWB",
            "BWWWB                                             BWWWB",
            "BWWWB                                             BWWWB",
            "BWWWB                                             BWWWB",
            "BWWWB                                             BWWWB",
            "BWWWB                                             BWWWB",
            "BWWWB                                             BWWWB",
            "BWWWWB                                           BWWWWB",
            " BWWWB                                           BWWWB ",
            " BWWWB                                           BWWWB ",
            " BWWWB                                           BWWWB ",
            "  BWWWB                                         BWWWB  ",
            "  BWWWB                                         BWWWB  ",
            "  BWWWWB                                       BWWWWB  ",
            "   BWWWB                                       BWWWB   ",
            "   BWWWWB                                     BWWWWB   ",
            "    BWWWWB                                   BWWWWB    ",
            "     BWWWWB                                 BWWWWB     ",
            "     BWWWWB                                 BWWWWB     ",
            "      BWWWWBB                             BBWWWWB      ",
            "       BWWWWWB                           BWWWWWB       ",
            "        BWWWWWB                         BWWWWWB        ",
            "         BWWWWWBB                     BBWWWWWB         ",
            "          BWWWWWWBB                 BBWWWWWWB          ",
            "           BBWWWWWWBBBB         BBBBWWWWWWBB           ",
            "             BWWWWWWWWWBBBBBBBBBWWWWWWWWWB             ",
            "              BBWWWWWWWWWWWWWWWWWWWWWWWBB              ",
            "                BBBWWWWWWWWWWWWWWWWWBBB                ",
            "                   BBBWWWWWWWWWWWBBB                   ",
            "                      BBBBBBBBBBB                      "
        },
        {
            "                      BBB     BBB                      ",
            "                   BBBWWW  ~  WWWBBB                   ",
            "                BBBWWWW---------WWWWBBB                ",
            "              BBWWW----WW     WW----WWWBB              ",
            "             BWWW--WWWWBB     BBWWWW--WWWB             ",
            "           BBWW--WWBBBB         BBBBWW--WWBB           ",
            "          BWW--WWBB                 BBWW--WWB          ",
            "         BWW-WWBB                     BBWW-WWB         ",
            "        BWW-WWB                         BWW-WWB        ",
            "       BWW-WWB                           BWW-WWB       ",
            "      BWW-WBB                             BBW-WWB      ",
            "     BWW-WB                                 BW-WWB     ",
            "     BW-WWB                                 BWW-WB     ",
            "    BW-WWB                                   BWW-WB    ",
            "   BWW-WB                                     BW-WWB   ",
            "   BW-WB                                       BW-WB   ",
            "  BWW-WB                                       BW-WWB  ",
            "  BW-WB                                         BW-WB  ",
            "  BW-WB                                         BW-WB  ",
            " BW-WB                                           BW-WB ",
            " BW-WB                                           BW-WB ",
            " BW-WB                                           BW-WB ",
            "BWW-WB                                           BW-WWB",
            "BW-WB                                             BW-WB",
            "BW-WB                                             BW-WB",
            "BW-WB                                             BW-WB",
            "BW-WB                                             BW-WB",
            "BW-WB                                             BW-WB",
            "BW-WB                                             BW-WB",
            "BW-WB                                             BW-WB",
            "BW-WB                                             BW-WB",
            "BW-WB                                             BW-WB",
            "BWW-WB                                           BW-WWB",
            " BW-WB                                           BW-WB ",
            " BW-WB                                           BW-WB ",
            " BW-WB                                           BW-WB ",
            "  BW-WB                                         BW-WB  ",
            "  BW-WB                                         BW-WB  ",
            "  BWW-WB                                       BW-WWB  ",
            "   BW-WB                                       BW-WB   ",
            "   BWW-WB                                     BW-WWB   ",
            "    BW-WWB                                   BWW-WB    ",
            "     BW-WWB                                 BWW-WB     ",
            "     BWW-WB                                 BW-WWB     ",
            "      BWW-WBB                             BBW-WWB      ",
            "       BWW-WWB                           BWW-WWB       ",
            "        BWW-WWB                         BWW-WWB        ",
            "         BWW-WWBB                     BBWW-WWB         ",
            "          BWW--WWBB                 BBWW--WWB          ",
            "           BBWW--WWBBBB         BBBBWW--WWBB           ",
            "             BWWW--WWWWBBBBBBBBBWWWW--WWWB             ",
            "              BBWWW----WWWWWWWWW----WWWBB              ",
            "                BBBWWWW---------WWWWBBB                ",
            "                   BBBWWWWWWWWWWWBBB                   ",
            "                      BBBBBBBBBBB                      "
        },
        {
            "                      BBB     BBB                      ",
            "                   BBBWWW     WWWBBB                   ",
            "                BBBWWWWWW     WWWWWWBBB                ",
            "              BBWWWWWWWWW     WWWWWWWWWBB              ",
            "             BWWWWWWWWWBB     BBWWWWWWWWWB             ",
            "           BBWWWWWWBBBB         BBBBWWWWWWBB           ",
            "          BWWWWWWBB                 BBWWWWWWB          ",
            "         BWWWWWBB                     BBWWWWWB         ",
            "        BWWWWWB                         BWWWWWB        ",
            "       BWWWWWB                           BWWWWWB       ",
            "      BWWWWBB                             BBWWWWB      ",
            "     BWWWWB                                 BWWWWB     ",
            "     BWWWWB                                 BWWWWB     ",
            "    BWWWWB                                   BWWWWB    ",
            "   BWWWWB                                     BWWWWB   ",
            "   BWWWB                                       BWWWB   ",
            "  BWWWWB                                       BWWWWB  ",
            "  BWWWB                                         BWWWB  ",
            "  BWWWB                                         BWWWB  ",
            " BWWWB                                           BWWWB ",
            " BWWWB                                           BWWWB ",
            " BWWWB                                           BWWWB ",
            "BWWWWB                                           BWWWWB",
            "BWWWB                                             BWWWB",
            "BWWWB                                             BWWWB",
            "BWWWB                                             BWWWB",
            "BWWWB                                             BWWWB",
            "BWWWB                                             BWWWB",
            "BWWWB                                             BWWWB",
            "BWWWB                                             BWWWB",
            "BWWWB                                             BWWWB",
            "BWWWB                                             BWWWB",
            "BWWWWB                                           BWWWWB",
            " BWWWB                                           BWWWB ",
            " BWWWB                                           BWWWB ",
            " BWWWB                                           BWWWB ",
            "  BWWWB                                         BWWWB  ",
            "  BWWWB                                         BWWWB  ",
            "  BWWWWB                                       BWWWWB  ",
            "   BWWWB                                       BWWWB   ",
            "   BWWWWB                                     BWWWWB   ",
            "    BWWWWB                                   BWWWWB    ",
            "     BWWWWB                                 BWWWWB     ",
            "     BWWWWB                                 BWWWWB     ",
            "      BWWWWBB                             BBWWWWB      ",
            "       BWWWWWB                           BWWWWWB       ",
            "        BWWWWWB                         BWWWWWB        ",
            "         BWWWWWBB                     BBWWWWWB         ",
            "          BWWWWWWBB                 BBWWWWWWB          ",
            "           BBWWWWWWBBBB         BBBBWWWWWWBB           ",
            "             BWWWWWWWWWBBBBBBBBBWWWWWWWWWB             ",
            "              BBWWWWWWWWWWWWWWWWWWWWWWWBB              ",
            "                BBBWWWWWWWWWWWWWWWWWBBB                ",
            "                   BBBWWWWWWWWWWWBBB                   ",
            "                      BBBBBBBBBBB                      "
        },
        {
            "                                                       ",
            "                      BBB     BBB                      ",
            "                   BBBBBB     BBBBBB                   ",
            "                BBBBBBBBB     BBBBBBBBB                ",
            "              BBBBBBBBB         BBBBBBBBB              ",
            "             BBBBBB                 BBBBBB             ",
            "           BBBBBB                     BBBBBB           ",
            "          BBBBB                         BBBBB          ",
            "         BBBBB                           BBBBB         ",
            "        BBBBB                             BBBBB        ",
            "       BBBB                                 BBBB       ",
            "      BBBB                                   BBBB      ",
            "      BBBB                                   BBBB      ",
            "     BBBB                                     BBBB     ",
            "    BBBB                                       BBBB    ",
            "    BBB                                         BBB    ",
            "   BBBB                                         BBBB   ",
            "   BBB                                           BBB   ",
            "   BBB                                           BBB   ",
            "  BBB                                             BBB  ",
            "  BBB                                             BBB  ",
            "  BBB                                             BBB  ",
            " BBBB                                             BBBB ",
            " BBB                                               BBB ",
            " BBB                                               BBB ",
            " BBB                                               BBB ",
            " BBB                                               BBB ",
            " BBB                                               BBB ",
            " BBB                                               BBB ",
            " BBB                                               BBB ",
            " BBB                                               BBB ",
            " BBB                                               BBB ",
            " BBBB                                             BBBB ",
            "  BBB                                             BBB  ",
            "  BBB                                             BBB  ",
            "  BBB                                             BBB  ",
            "   BBB                                           BBB   ",
            "   BBB                                           BBB   ",
            "   BBBB                                         BBBB   ",
            "    BBB                                         BBB    ",
            "    BBBB                                       BBBB    ",
            "     BBBB                                     BBBB     ",
            "      BBBB                                   BBBB      ",
            "      BBBB                                   BBBB      ",
            "       BBBB                                 BBBB       ",
            "        BBBBB                             BBBBB        ",
            "         BBBBB                           BBBBB         ",
            "          BBBBB                         BBBBB          ",
            "           BBBBBB                     BBBBBB           ",
            "             BBBBBB                 BBBBBB             ",
            "              BBBBBBBBB         BBBBBBBBB              ",
            "                BBBBBBBBBBBBBBBBBBBBBBB                ",
            "                   BBBBBBBBBBBBBBBBB                   ",
            "                      BBBBBBBBBBB                      ",
            "                                                       "
        }
    });
}
