# whistle


####IssueController
* ISSUE_URL_ROOT = "/issue";
* CREATE_NEW_ISSUE = "/create";
* GET_ALL_ISSUES_FOR_ADMIN = "/get-all";
* GET_ALL_ISSUES_FOR_LAWYER = "/get-all-lawyer";
* ASSIGN_ISSUE = "/assign"; // (Admin)
* CHANGE_ISSUE_STATUS = "/change-status";
* INACTIVATE_ISSUE = "/active";
* CHANGE_CATEGORY = "/change-category";

####issueStatusController
* ISSUE_STATUS_URL_ROOT = "/issue-status";
* GET_ALL_ISSUE_STATUS = "/get-all";
* GET_ISSUE_STATUS_USER = "/user";

####UserController
* USER_URL_ROOT = "/user";
* GET_ALL_LAWYERS = "/lawyers";
* SET_GDPR_CONSENT ="/gdpr-consent";

####CategoryController
* CATEGORY_URL_ROOT = "/category";
* REMOVE_CATEGORY = "/remove";
* ADD_CATEGORY = "/add";
* GET_CATEGORIES = "/get-all";

####PostBoxController
* POSTBOX_URL_ROOT = "/post";
* POSTBOX_SEND_BY_LAWYER = "/send";
* POSTBOX_REPLY_BY_USER = "/reply";
* POSTBOX_GET_ALL_FOR_LAWYER = "/get-lawyer";
* POSTBOX_GET_ALL_FOR_USER = "/get-user";

####FileUploadController
* FILE_DOWNLOAD = "/download";