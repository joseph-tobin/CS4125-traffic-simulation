public class TCMFactory<T> {

    // TODO: 24-10-19 properly implement TCMFactory - consider how each TCM will be different and generalize

    public static TCM getTCM() {
        return T.create();
    }
    public static TCM getTCM(Node[] adj) {
        return T.create();
    }

}