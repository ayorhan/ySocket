package com.ayorhan.android.ySocket;

import android.os.Build;
import android.os.Handler;

/**
 * Created with IntelliJ IDEA.
 * User: ayorhan
 * Date: 5/29/13
 * Time: 2:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class YSocketMessageHandler {

    //TODO Data Structure to add handlers
    /*public static NotificationHandler notificationHandler = new NotificationHandler(CanakOkeyGamePlay.notifier);
    public static NotificationHandler notificationSelectRoomHandler = new NotificationHandler(SelectRoomActivity.notifier);
    public static NotificationHandler notificationSelectTableHandler = new NotificationHandler(SelectTableActivity.notifier);
    public static NotificationHandler notificationSelectLobbyHandler = new NotificationHandler(SelectLobbyActivity.notifier);
    public static NotificationHandler notificationMainMenuHandler = new NotificationHandler(MainMenuActivity.notifier);

    public static String socketID;
    private static final Handler handler = new Handler();

    public static void sendMessage(SocketConnectionManager manager, CanakConstants.MessageType messageType, CanakConstants.Command command, String parameters){
        try{

            CanakLog.d("About to call writeToGateway");
            if (Integer.valueOf(Build.VERSION.SDK) < 11)
                new WriteToGatewaySocketAsyncTask(manager).execute(new XMLSocketMessage(messageType,parameters,command,manager.getSocketNumber(command), socketID));
            else
                new WriteToGatewaySocketAsyncTask(manager).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new XMLSocketMessage(messageType,parameters,command,manager.getSocketNumber(command), socketID));

        } catch (NullPointerException e){
            e.printStackTrace();
            Crittercism.logHandledException(e);
        } catch (Exception e){
            e.printStackTrace();
            Crittercism.logHandledException(e);
        }
    }

    public static void sendMessage(SocketConnectionManager manager, Context context, Integer messageID, CanakConstants.MessageType messageType, CanakConstants.Command command, String parameters){
        try{

            CanakLog.d("About to call writeToGateway");
            if (Integer.valueOf(Build.VERSION.SDK) < 11)
                new WriteToGatewaySocketAsyncTask(manager, context, messageID).execute(new XMLSocketMessage(messageType,parameters,command,manager.getSocketNumber(command), socketID));
            else
                new WriteToGatewaySocketAsyncTask(manager, context, messageID).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new XMLSocketMessage(messageType,parameters,command,manager.getSocketNumber(command), socketID));

        } catch (NullPointerException e){
            e.printStackTrace();
            Crittercism.logHandledException(e);
        } catch (Exception e){
            e.printStackTrace();
            Crittercism.logHandledException(e);
        }
    }

    public static void initGameServer(SocketConnectionManager manager, String fuid, String server, String port,
                                      String lobbyid, String secret){
        CanakLog.d("initGameServer");

        String parameters = String.format("%s;%s;%s;%s;%s;android;%s;%s",
                lobbyid,
                fuid,
                "oyun.mynet.com",
                "1",
                secret,
                server,
                port);

        sendMessage(manager, CanakConstants.MessageType.GAME, CanakConstants.Command.SEND_INIT_PARAMETERS, parameters);
    }

    public static void switchServer(SocketConnectionManager manager, Context context, String server, String port, String lobbyID){
        CanakLog.d("switchServer");
        String parameters = String.format("%s:%s:%s", server, port, lobbyID);
        manager.resetSocketNumber();
        sendMessage(manager, context, R.string.connecting_gateway_progress, CanakConstants.MessageType.GAME, CanakConstants.Command.SWITCH_SERVER, parameters);
    }

    public static void leaveRoom(SocketConnectionManager manager){
        CanakLog.d("leave room");
        String parameters = "";
        sendMessage(manager, CanakConstants.MessageType.GAME, CanakConstants.Command.LEAVE_ROOM, parameters);
    }

    public static void getFriends(SocketConnectionManager manager){
        CanakLog.d("getFriends");
        String parameters = "0";
        sendMessage(manager, CanakConstants.MessageType.GAME, CanakConstants.Command.GET_FRIENDS, parameters);
    }

    public static void getOnlineFriendsCount(SocketConnectionManager manager){
        CanakLog.d("getOnlineFriendsCount");
        String parameters = "0";
        sendMessage(manager, CanakConstants.MessageType.GAME, CanakConstants.Command.GET_ONLINE_FRIENDS_COUNT, parameters);
    }

    public static void enterRoom(SocketConnectionManager manager, String roomID){
        CanakLog.d("enterRoom");
        String parameters = String.format("%s",roomID);
        sendMessage(manager, CanakConstants.MessageType.GAME, CanakConstants.Command.ENTER_ROOM, parameters);
    }

    public static void enterTable(SocketConnectionManager manager, String tableID){
        CanakLog.d("enterTable");
        String parameters = String.format("%s", tableID);
        sendMessage(manager, CanakConstants.MessageType.GAME, CanakConstants.Command.JOIN_AN_AUDIENCE, parameters);
    }

    public static void getRoomsUsersCount(SocketConnectionManager manager){
        CanakLog.d("getRoomsUsersCount");
        String parameters = "0";
        sendMessage(manager, CanakConstants.MessageType.GAME, CanakConstants.Command.GET_ROOMS_USERS_COUNT, parameters);
    }

    public static void getLobbyStatusList(SocketConnectionManager manager){
        CanakLog.d("getLobbyStatusList");
        String parameters = "0";
        sendMessage(manager, CanakConstants.MessageType.GAME, CanakConstants.Command.GET_NETWORK_LIST, parameters);
    }

    public static void getFriendsInRoom(SocketConnectionManager manager, String fuid){
        CanakLog.d("getFriendsInRoom");
        String parameters = String.format("%s",
                fuid);
        sendMessage(manager, CanakConstants.MessageType.GAME, CanakConstants.Command.GET_FRIENDS_IN_ROOM, parameters);
    }

    public static void getUsersInRoom(SocketConnectionManager manager){
        CanakLog.d("getUsersInRoom");
        String parameters = "0";
        sendMessage(manager, CanakConstants.MessageType.GAME, CanakConstants.Command.GET_USERS_IN_ROOM, parameters);
    }

    public static void startNextRound(SocketConnectionManager manager){
        CanakLog.d("startNextRound");
        String parameters = "0";
        sendMessage(manager, CanakConstants.MessageType.GAME, CanakConstants.Command.START_NEXT_ROUND, parameters);
    }

    public static void setTablesCounts(SocketConnectionManager manager){
        CanakLog.d("setTablesCounts");
        String parameters = "0";
        sendMessage(manager, CanakConstants.MessageType.GAME, CanakConstants.Command.SET_TABLES_COUNTS, parameters);
    }
    // Sits to selected side of table
    public static void sitTable(SocketConnectionManager manager, Context context, String side){
        CanakLog.d("sitTable");
        String parameters = String.format("%s",
                side);
        sendMessage(manager, context, R.string.sit_table_progress, CanakConstants.MessageType.GAME, CanakConstants.Command.SIT_TABLE, parameters);
    }
    // Gets tas from deck or other user
    public static void getTasFromDeck(SocketConnectionManager manager, String tableID,
                                      int side, Tas tas, int location){
        //Location 1 desteden, 0 soldaki adamin attigi
        CanakLog.d("getCardFromDeck");
        String parameters = String.format("%s;%d;%d;%d;%d;%d",
                tableID,
                side,
                tas.getDeck(),
                tas.getColor(),
                tas.getNumber(),
                location);
        sendMessage(manager, CanakConstants.MessageType.GAME, CanakConstants.Command.GET_CARD_FROM_DECK, parameters);
    }
    // Sends tas
    public static void sendTas(SocketConnectionManager manager, String tableID, int side, Tas tas){
        CanakLog.d("sendUserAction");
        String parameters = String.format("%s;%d;%d;%d;%d",
                tableID,
                side,
                tas.getColor(),
                tas.getNumber(),
                tas.getDeck());
        sendMessage(manager, CanakConstants.MessageType.GAME, CanakConstants.Command.SEND_USER_ACTION, parameters);
    }
    // Sends go double action
    public static void sendGoDouble(SocketConnectionManager manager, String tableID, int side, String userID){
        CanakLog.d("sendGoDouble");
        String parameters = String.format("%s;%d;%s",
                tableID,
                side,
                userID
        );
        sendMessage(manager, CanakConstants.MessageType.GAME, CanakConstants.Command.SEND_GO_DOUBLE, parameters);
    }
    // Sends get thrown cards command, sadece cifte gidenler gonderebiliyor. Atilan taslari gosterir.
    public static void getThrownCards(SocketConnectionManager manager, String tableID){
        CanakLog.d("getThrownCards");
        String parameters = String.format("%s",
                tableID);
        sendMessage(manager, CanakConstants.MessageType.GAME, CanakConstants.Command.GET_THROWN_CARDS, parameters);
    }
    // Ends the game
    public static void sendHandOver(SocketConnectionManager manager, Context context, String tableID, int side, String userID,
                                    boolean isGoDouble, ArrayList<PieceCell> taslar, PieceCell atilanTas){
        CanakLog.d("sendHandOver");

        StringBuilder eldekiTaslarParameters = new StringBuilder();
        for (int i=0; i<taslar.size(); i++){
            PieceCell pieceCell = taslar.get(i);
            int slotNumber = pieceCell.mCellNumber;
            int posX;
            int posY;

            if (slotNumber < 14){
                posX = slotNumber;
                posY = 1;
            } else {
                posX = slotNumber -13;
                posY = 2;
            }

            eldekiTaslarParameters.append(String.format("%d_%d_%d_%d_%d",
                    pieceCell.getmDeck(),
                    pieceCell.getmType().getId(),
                    pieceCell.getmValue(),
                    posX,
                    posY));

            eldekiTaslarParameters.append("~");
        }



        String goDoubleParameter = "";
        if (isGoDouble){
            goDoubleParameter = "1";
        } else {
            goDoubleParameter = "0";
        }

        String parameters = String.format("%s;%d;%d;%d;%s;%s;%s",
                tableID,
                side,
                atilanTas.getmType().getId(),
                atilanTas.getmValue(),
                userID,
                goDoubleParameter,
                eldekiTaslarParameters.toString());
        sendMessage(manager, context, R.string.send_hand_over_progress, CanakConstants.MessageType.GAME, CanakConstants.Command.SEND_HAND_OVER, parameters);
    }
    // Lefts table
    public static void leftTableVolunteer(SocketConnectionManager manager, String tableID){
        CanakLog.d("leftTableVolunteer");
        String parameters = String.format("%s",
                tableID);
        sendMessage(manager, CanakConstants.MessageType.GAME, CanakConstants.Command.LEFT_TABLE_VOLUNTEER, parameters);
    }
    // Gets game status
    public static void getGameStatus(SocketConnectionManager manager, String tableID){
        CanakLog.d("getGameStatus");
        String parameters = String.format("%s",
                tableID);
        sendMessage(manager, CanakConstants.MessageType.GAME, CanakConstants.Command.GET_GAME_STATUS, parameters);
    }

    public static void sendChatMessage(SocketConnectionManager manager, String tableID, String fuid, String msg){
        CanakLog.d("sendChatMessage");
        String parameters = String.format("%s;%s;%s",
                tableID,
                fuid,
                msg);
        sendMessage(manager, CanakConstants.MessageType.CHAT, CanakConstants.Command.GET_GAME_STATUS, parameters);
    }

    public static void sendQuickPlay(SocketConnectionManager manager, String tableID){
        CanakLog.d("sendQuickPlay");
        String parameters = String.format("%s",
                tableID);
        sendMessage(manager, CanakConstants.MessageType.GAME, CanakConstants.Command.QUICK_PLAY, parameters);
    }

    public static void sendSuspendMessage(SocketConnectionManager manager){
        CanakLog.d("sendSuspendMessage");
        String parameters = "a";
        sendMessage(manager, CanakConstants.MessageType.GAME, CanakConstants.Command.SUSPEND, parameters);
    }

    public static void sendAwakeMessage(SocketConnectionManager manager){
        CanakLog.d("sendAwakeMessage");
        String parameters = "";
        sendMessage(manager, CanakConstants.MessageType.GAME, CanakConstants.Command.AWAKE, parameters);
    }

    public static void sendVIPStatusUpdateMessage(SocketConnectionManager manager){
        CanakLog.d("sendVIPStatusUpdateMessage");
        String parameters = "";
        sendMessage(manager, CanakConstants.MessageType.GAME, CanakConstants.Command.VIP_STATUS_UPDATED, parameters);
    }

    public static void processIncomingMessage(XMLSocketMessage incoming){
        CanakLog.d("command: " + incoming.getCommand().name() + " type: "+ incoming.getMessageType().name() + " parameters: " + incoming.getParametersString());
        String [] paramArray;

        if (incoming.getMessageType() == CanakConstants.MessageType.CHAT){
            String [] parameters = incoming.getParametersString().split(";");
            if (parameters.length >= 2){
                ChatMessage chatMessage = new ChatMessage(parameters);
                handler.post(new MessageRunnable(new UpdateNotification(incoming.getCommand(), chatMessage)));
            }

        } else {
            //getCurrentActivity();
            switch (incoming.getCommand()) {

                case SEND_NICKNAME:
                    CanakLog.d("SEND_NICKNAME");
                    handler.post(new MessageRunnable(new UpdateNotification(incoming.getCommand(), new UserDetails(incoming.getParametersString()))));
                    handler.post(new MessageRunnable(new UpdateNotification(incoming.getCommand(), new UserDetails(incoming.getParametersString())), CanakConstants.RunnableType.SELECT_ROOM));
                    handler.post(new MessageRunnable(new UpdateNotification(incoming.getCommand(), new UserDetails(incoming.getParametersString())), CanakConstants.RunnableType.MAIN_MENU));
                    handler.post(new MessageRunnable(new UpdateNotification(incoming.getCommand(), new UserDetails(incoming.getParametersString())), CanakConstants.RunnableType.SELECT_LOBBY));
                    break;
                case ENTER_ROOM:
                    CanakLog.d("ENTER_ROOM");
                    handler.post(new MessageRunnable(new UpdateNotification(incoming.getCommand(), incoming.getParametersString())));
                    break;
                case GET_ROOMS_USERS_COUNT:
                    CanakLog.d("GET_ROOMS_USERS_COUNT");
                    paramArray = incoming.getParametersString().split(",");

                    HashMap<String, String> roomCountMap = new HashMap<String, String>();
                    for (String roomParameter : paramArray) {
                        String[] roomParameterArray = roomParameter.split("\\.");
                        if (roomParameterArray.length >= 2)
                            roomCountMap.put(roomParameterArray[0], roomParameterArray[1]);
                    }
                    handler.post(new MessageRunnable(new UpdateNotification(incoming.getCommand(), roomCountMap), CanakConstants.RunnableType.SELECT_ROOM));
                    break;
                case GET_NETWORK_LIST: //Sets Lobby user counts and status
                    CanakLog.d("GET_NETWORK_LIST");
                    paramArray = incoming.getParametersString().split("#");
                    handler.post(new MessageRunnable(new UpdateNotification(incoming.getCommand(), paramArray), CanakConstants.RunnableType.SELECT_LOBBY));
                    break;
                case GET_ALL_PLAYERS:
                    break;
                case REMOVE_USER_FROM_ROOM:
                    break;
                case JOIN_TABLE:
                    break;
                case LEFT_TABLE_VOLUNTEER:
                    break;
                case SIT_TABLE:
                    break;
                case JOIN_AN_OPPONENT: // Received when an opponent sits to table
                    CanakLog.d("JOIN_AN_OPPENENT");
                    paramArray = incoming.getParametersString().split(";");
                    HashMap<String, String> joinAnOpponentMap = new HashMap<String, String>();
                    if (paramArray.length >= 2) {
                        joinAnOpponentMap.put("side", paramArray[0]);
                        joinAnOpponentMap.put("fuid", paramArray[1]);
                    }
                    handler.post(new MessageRunnable(new UpdateNotification(incoming.getCommand(), joinAnOpponentMap)));
                    break;
                case JOIN_AN_AUDIENCE: // Gets players and followers in the table with table status and table id
                    CanakLog.d("JOIN_AN_AUDIENCE");
                    TableStatus tableStatus = new TableStatus(incoming.getParametersString());
                    handler.post(new MessageRunnable(new UpdateNotification(incoming.getCommand(), tableStatus)));
                    break;
                case LEFT_AN_AUDIENCE: // Received when an audience left the table
                    CanakLog.d("LEFT_AN_AUDIENCE");
                    //LOOKS LIKE NOT USED
                    break;
                case LEFT_A_GAMER: // Received when a player left the table
                    CanakLog.d("LEFT_A_GAMER");
                    paramArray = incoming.getParametersString().split(";");
                    HashMap<String, String> userLeftMap = new HashMap<String, String>();
                    if (paramArray.length >= 3) {
                        userLeftMap.put("fuid", paramArray[0]);
                        userLeftMap.put("tableID", paramArray[1]);
                        userLeftMap.put("side", paramArray[2]);
                    }
                    handler.post(new MessageRunnable(new UpdateNotification(incoming.getCommand(), userLeftMap)));
                    break;
                case USER_SIT_A_TABLE: // Received when user sits to table
                    CanakLog.d("USER_SIT_A_TABLE");
                    //TODO
                    paramArray = incoming.getParametersString().split(";");
                    HashMap<String, String> userSitTableMap = new HashMap<String, String>();
                    if (paramArray.length >= 5) {
                        userSitTableMap.put("tableID", paramArray[0]);
                        userSitTableMap.put("side", paramArray[1]);
                        userSitTableMap.put("fuid", paramArray[2]);
                        userSitTableMap.put("isAudience", paramArray[3]);
                        userSitTableMap.put("gameStatus", paramArray[4]);
                    }
                    handler.post(new MessageRunnable(new UpdateNotification(incoming.getCommand(), userSitTableMap)));
                    break;
                case GET_TABLE_STATUS:
                    break;
                case START_GAME: // Received all users sit on table or when the next round starts
                case START_NEXT_ROUND: // Received all users sit on table or when the next round starts
                    StartGame startGame = new StartGame(incoming.getParametersString());
                    CanakLog.d("START_GAME");
                    handler.post(new MessageRunnable(new UpdateNotification(incoming.getCommand(), startGame)));
                    break;
                case SEND_USER_ACTION: // Received when a user sends a tas
                    CanakLog.d("SEND_USER_ACTION");
                    AtilanTas atilanTas = new AtilanTas(incoming.getParametersString());
                    handler.post(new MessageRunnable(new UpdateNotification(incoming.getCommand(), atilanTas)));
                    break;
                case GET_TOP_THROWN_CARD:
                    break;
                case SEND_OPEN_HAND:
                    break;
                case SEND_GO_DOUBLE: // Received when a user goes double
                    CanakLog.d("SEND_GO_DOUBLE");
                    paramArray = incoming.getParametersString().split(";");
                    if (paramArray.length > 0) {
                        handler.post(new MessageRunnable(new UpdateNotification(incoming.getCommand(), paramArray[0])));
                    }
                    break;
                case SEND_TABLE_OPTIONS:
                    break;
                case GET_CARDS_AND_SCORES:
                    break;
                case SEND_TABLE_INFO: // Received table info message
                    CanakLog.d("SEND_TABLE_INFO");
                    TableInfo tableInfo = new TableInfo(incoming.getParametersString());
                    handler.post(new MessageRunnable(new UpdateNotification(incoming.getCommand(), tableInfo)));
                    break;
                case RESTART_GAME:
                    break;
                case JOINED_TO_STOPPED_GAME:
                    //NOT USED
                    CanakLog.d("JOINED_TO_STOPPED_GAME");
                    break;
                case ISLEK_TO_OPEN_CARDS:
                    break;
                case EXCHANGE_ISLEK_CARDS:
                    break;
                case GET_CARD_FROM_DECK: // Received when a user gets a tas from deck or from other user
                    CanakLog.d("GET_CARD_FROM_DECK");
                    CekilenTas cekilenTas = new CekilenTas(incoming.getParametersString());
                    handler.post(new MessageRunnable(new UpdateNotification(incoming.getCommand(), cekilenTas)));
                    break;
                case SEND_HAND_OVER: // El bitimi
                    CanakLog.d("SEND_HAND_OVER");
                    if (incoming.getParametersString().length() > 5) {
                        //HAND OVER TRUE
                        HandOver handOver = new HandOver(incoming.getParametersString());
                        handler.post(new MessageRunnable(new UpdateNotification(incoming.getCommand(), handOver)));
                    } else {
                        //HAND OVER FALSE
                        handler.post(new MessageRunnable(new UpdateNotification(CanakConstants.Command.SEND_HAND_OVER_NOT_FINISHED)));
                    }
                    break;
                case CHANGE_FACE_ID:
                    break;
                case GAME_WITH_NO_END:
                    break;
                case GAME_OVER:
                    break;
                case PUSH_START_GAME:
                    break;
                case SIT_AVAILABLE_TABLE:
                    break;
                case SEND_INIT_PARAMETERS:
                    break;
                case KICK_USER_FROM_TABLE: // Implemented
                    CanakLog.d("KICK_USER_FROM");
                    handler.post(new MessageRunnable(new UpdateNotification(incoming.getCommand(), incoming.getParametersString())));
                    break;
                case SEND_INVITE_REQUEST:
                    CanakLog.d("SEND_INVITE_REQUEST");
                    //NOT USED
                    break;
                case SEND_ANSWER_TO_INVITE:
                    break;
                case INVALID_LOGIN: // Invalid login received
                    CanakLog.d("INVALID_LOGIN");
                    int message = getInvalidLoginMessage(incoming.getParametersString());
                    handler.post(new MessageRunnable(new UpdateNotification(incoming.getCommand(), message)));
                    handler.post(new MessageRunnable(new UpdateNotification(incoming.getCommand(), message), CanakConstants.RunnableType.SELECT_ROOM));
                    handler.post(new MessageRunnable(new UpdateNotification(incoming.getCommand(), message), CanakConstants.RunnableType.SELECT_TABLE));
                    handler.post(new MessageRunnable(new UpdateNotification(incoming.getCommand(), message), CanakConstants.RunnableType.SELECT_LOBBY));
                    handler.post(new MessageRunnable(new UpdateNotification(incoming.getCommand(), message), CanakConstants.RunnableType.MAIN_MENU));
                    break;
                case GET_FRIENDS:
                    break;
                case SEND_PROFILE_REQUEST:
                    break;
                case GET_INFO_FOR_WATCH:
                    break;
                case SEND_EMPTY_ACTION:
                    break;
                case ADD_TO_FRIEND_LIST_REQUEST:
                    break;
                case ADD_TO_FRIEND_LIST_ANSWER:
                    break;
                case NOTIFY_FOR_AUDIENCE: // Received when user enters to room
                    CanakLog.d("NOTIFY_FOR_AUDIENCE");
                    String[] parameters = incoming.getParametersString().split(";");
                    ArrayList<GameUser> userArray = new ArrayList<GameUser>();
                    for (String userData : parameters) {
                        String[] userParameters = userData.split("#");
                        GameUser gameUser = new GameUser(userParameters, CanakConstants.Command.NOTIFY_FOR_AUDIENCE);
                        userArray.add(gameUser);
                    }
                    handler.post(new MessageRunnable(new UpdateNotification(incoming.getCommand(), userArray)));
                    break;
                case GET_CARD_FROM_DECK_PUNISH:
                    break;
                case SOCKET_DISCONNECT:
                    break;
                case ADD_CARD_TO_PER:
                    break;
                case ADD_NEW_PER_AFTER_OPEN:
                    break;
                case SWAP_REAL_OKEY:
                    break;
                case OPEN_HAND_IMMEDIATELY:
                    break;
                case GIVE_BACK_SOUTH:
                    break;
                case SEND_TABLE_OPTIONS_GAME_TYPE:
                    break;
                case ADD_TO_FAVORITE_ROOMS:
                    break;
                case SEND_HAND_DRAW: // Hand draw received
                    CanakLog.d("SEND_HAND_DRAW");
                    handler.post(new MessageRunnable(new UpdateNotification(incoming.getCommand())));
                    break;
                case GET_GAME_STATUS: // Received when game status requested
                    CanakLog.d("GET_GAME_STATUS");
                    handler.post(new MessageRunnable(new UpdateNotification(incoming.getCommand(), incoming.getParametersString())));
                    break;
                case GET_THROWN_CARDS:
                    CanakLog.d("GET_THROWN_CARDS");
                    //LOOKS LIKE NOT USED
                    break;
                case GET_TABLE_LIST:
                    break;
                case GET_USERS_IN_LOBBY:
                    break;
                case MAKE_USER_ADMIN:
                    break;
                case ADMIN_BAN_USER:
                    break;
                case GET_FRIENDS_FUID:
                    break;
                case SEND_IDLE_SIGNAL:
                    break;
                case ADMIN_KICK_USER:
                    break;
                case ADMIN_SPECIAL_KICK:
                    break;
                case KICK_FROM_ROOM:
                    break;
                case IS_DAILY_POST:
                    break;
                case GET_TABLE_CONTENTS:
                    break;
                case UPDATE_TABLE_COUNTS:
                    break;
                case UPDATE_USERS_EXPERIENCE:
                    break;
                case UPDATE_USERS_MONEY:
                    break;
                case LEFT_GAME_FOR_MONEY:
                    break;
                case GET_PROFILE_DETAILS:
                    break;
                case IS_ON_FACEBOOK:
                    break;
                case SEND_GIFT:
                    break;
                case RECEIVE_GIFT:
                    break;
                case GET_USER_GIFTS:
                    break;
                case DELETE_FRIEND:
                    break;
                case GET_ONLINE_FRIENDS:
                    break;
                case SELL_GIFT:
                    break;
                case USER_SOLD_GIFT:
                    break;
                case GIFT_ERROR:
                    break;
                case PROFILE_GIFT_UPDATE:
                    break;
                case GET_ONLINE_FRIENDS_COUNT: // Gets online friends count
                    CanakLog.d("GET_ONLINE_FRIENDS_COUNT");
                    //TODO set count of number of online friends, parameter is an int
                    handler.post(new MessageRunnable(new UpdateNotification(incoming.getCommand(), incoming.getParametersString())));
                    break;
                case LEAVE_ROOM:
                    break;
                case SET_TABLES_COUNTS: // Sets table counts in a room
                    CanakLog.d("SET_TABLES_COUNTS");
                    paramArray = incoming.getParametersString().split(";");
                    HashMap<String, String> tableCountMap = new HashMap<String, String>();

                    for (String tableStr : paramArray) {
                        String[] tableParams = tableStr.split(",");
                        if (tableParams.length == 2) {
                            tableCountMap.put(tableParams[0], tableParams[1]);
                        }
                    }

                    if (tableCountMap.keySet().size() >= 12) {
                        handler.post(new MessageRunnable(new UpdateNotification(incoming.getCommand(), tableCountMap), CanakConstants.RunnableType.SELECT_TABLE));
                    }
                    break;
                case GET_USERS_IN_ROOM:
                    CanakLog.d("GET_USERS_IN_ROOM");
                    //NOT USED
                    break;
                case GET_FRIENDS_IN_ROOM:
                    CanakLog.d("GET_FRIENDS_IN_ROOM");
                    //NOT USED
                    break;
                case USER_RECEIVED_MONEY:
                    break;
                case USER_RECEIVED_MONEY_TABLE:
                    break;
                case SERVER_MESSAGE:
                    break;
                case UPDATE_USER_OPTIONS:
                    break;
                case OPEN_LOG:
                    break;
                case REPEAT_LAST_MSG:
                    break;
                case QUICK_PLAY: // QuickPlay tableID received
                    CanakLog.d("QUICK_PLAY");
                    handler.post(new MessageRunnable(new UpdateNotification(incoming.getCommand(), incoming.getParametersString())));
                    break;
                case USER_PREVENT_PCHAT:
                    break;
                case VIP_STATUS_UPDATED:
                    break;
                case VIP_STATUS_UPDATED_TABLE:
                    break;
                case SEND_VIP_TABLE_MESSAGE:
                    break;
                case SEND_CONGRATS_TO_FRIEND_POT:
                    break;
                case GIVE_MONEY_FOR_CONGRAT:
                    break;
                case CALL_SLOT_MACHINE:
                    break;
                case SEND_JACKPOT:
                    CanakLog.d("SEND_JACKPOT");
                    break;
                case SUSPEND:
                    break;
                case AWAKE:
                    CanakLog.d("AWAKE");
                    if (incoming.getParametersString().length() == 0 || incoming.getParametersString().equals("-1")) {
                        //AWAKE_FROM_ENDED_GAME
                        handler.post(new MessageRunnable(new UpdateNotification(CanakConstants.Command.AWAKE_FROM_ENDED_GAME)));
                    } else { //AWAKE_MESSAGE
                        AwakeMessage awakeMessage = new AwakeMessage(incoming.getParametersString());
                        handler.post(new MessageRunnable(new UpdateNotification(CanakConstants.Command.AWAKE_MESSAGE, awakeMessage)));
                    }
                    break;

                default:
                    //TODO decide how to handle this. Maybe just discard it?
                    break;
            }
        }
    }

    private static int getInvalidLoginMessage(String messageID) {
        int message = -1;
        if (messageID != null && messageID.length()>0){
            if (messageID.equals("1")){
                return R.string.msg1;
            } else if (messageID.equals("2")){
                return R.string.msg2;
            } else if (messageID.equals("3")){
                return R.string.msg3;
            } else if (messageID.equals("4")){
                return R.string.msg4;
            } else if (messageID.equals("5")){
                return R.string.msg5;
            } else if (messageID.equals("6")){
                return R.string.msg6;
            } else if (messageID.equals("7")){
                return R.string.msg7;
            } else if (messageID.equals("8")){
                return R.string.msg8;
            }
        }

        message = R.string.dialog_message;
        return message;
    }

    private static class MessageRunnable implements Runnable {

        private final UpdateNotification updateNotification;
        private CanakConstants.RunnableType type;


        MessageRunnable(final  UpdateNotification updateNotification){
            this.updateNotification = updateNotification;
            this.type = CanakConstants.RunnableType.GAME_PLAY;
        }

        MessageRunnable(final  UpdateNotification updateNotification, CanakConstants.RunnableType type){
            this.updateNotification = updateNotification;
            this.type = type;
        }
        @Override
        public void run() {

            switch (type) {
                case GAME_PLAY:
                    notificationHandler.notify(updateNotification);
                    break;
                case SELECT_ROOM:
                    notificationSelectRoomHandler.notify(updateNotification);
                    break;
                case SELECT_TABLE:
                    notificationSelectTableHandler.notify(updateNotification);
                    break;
                case SELECT_LOBBY:
                    notificationSelectLobbyHandler.notify(updateNotification);
                    break;
                case MAIN_MENU:
                    notificationMainMenuHandler.notify(updateNotification);
                    break;
            }
        }
    }   */
}
