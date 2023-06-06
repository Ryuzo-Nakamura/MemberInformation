package jp.co.aforce.constant;

public class Constant {
	
	private Constant() {}
	
	/*
	 *  定数
	 */
	
	// 項目
	public static class Item {
		public static final String MEMBER_ID = "会員番号";
		public static final String LAST_NAME = "名前_姓";
		public static final String FIRST_NAME = "名前_名";
		public static final String GENDER = "性別";
		public static final String BIRTH_YEAR = "生年月日_年";
		public static final String BIRTH_MONTH = "生年月日_月";
		public static final String BIRTH_DAY = "生年月日_日";
		public static final String PHONE_NUMBER = "電話番号";
		public static final String MAIL＿ADDRESS = "メールアドレス";
		public static final String JOB = "職業";
	}
	
	// 性別コード
	public static class Gender{
		public static final String KK_00011 = "男";
		public static final String KK_00012 = "女";
	}
	
	// 職業コード
	public static class Job{
		public static final String KK_0002100 = "会社員";
		public static final String KK_0002200 = "自営業";
		public static final String KK_0002300 = "学生";
		public static final String KK_0002400 = "その他";
		
		public static String[] getJobCodes() {
			String[] codes = {KK_0002100, KK_0002200, KK_0002300, KK_0002400};
			return codes;
		}
	}
	
	// メッセージ
	public static class Message{
		public static final String W_CMM0001 = "{0}は必須入力項目です。";
		public static final String E_WMM0001 = "会員情報が既に登録されています。";
		public static final String E_WMM0002 = "会員情報登録に失敗しました。";
		public static final String E_WMM0003 = "会員情報更新に失敗しました。";
		public static final String E_WMM0004 = "会員情報削除に失敗しました。";
		public static final String E_WMM0005 = "この会員番号は存在しません。";
		public static final String E_WMM0006 = "この電話番号は既に使用されています。";
		public static final String E_WMM0007 = "このメールアドレスは既に使用されています。";
		public static final String I_WMM0001 = "会員情報登録が完了しました。";
		public static final String I_WMM0002 = "会員番号'{0}' の会員情報更新が完了しました。";
		public static final String I_WMM0003 = "会員番号'{0}' の会員情報削除が完了しました。";
	}
}
