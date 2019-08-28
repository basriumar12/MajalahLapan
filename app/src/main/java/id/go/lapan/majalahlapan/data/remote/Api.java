package id.go.lapan.majalahlapan.data.remote;

import id.go.lapan.majalahlapan.model.announcement.ResponseAnnouncement;
import id.go.lapan.majalahlapan.model.archive.ResponseArchive;
import id.go.lapan.majalahlapan.model.archive.ResponseArchives;
import id.go.lapan.majalahlapan.model.current.ResponseAuthor;
import id.go.lapan.majalahlapan.model.current.ResponseCurrent;
import id.go.lapan.majalahlapan.model.current.ResponseCurrentNew;
import id.go.lapan.majalahlapan.model.last_issue.ResponseLastIssue;
import id.go.lapan.majalahlapan.model.notification.ResponseNotification;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

public interface Api {
    @GET("api_announcement_byJouID.php")
    Call<List<ResponseAnnouncement>> requestDataAnnouncement(
            @Query("journal_id") String journalID
    );

    @GET("api_issue.php")
    Call<List<ResponseArchive>> requestDataArchive();

    //get data for current by jurnal id
    @GET("api_issue.php")
    Call<List<ResponseCurrentNew>> requestDataArchivebyJournalID(
            @Query("id") String journalID
    );

    //get data arcchive by jurnal id
    @GET("api_issue.php")
    Call<List<ResponseArchives>> requestDataArchives(
            @Query("id") String journalID
    );


    @GET("api_issue_last.php")
    Call<List<ResponseLastIssue>> requestLastissueId(
            @Query("id") String journalID
    );


    @GET("api_issue_year.php")
    Call<List<ResponseArchive>> requestDataChildArchive(
            @Query("year") String year
    );

    @GET("api_issue_year.php")
    Call<List<ResponseCurrentNew>> requestDataIssueByYear(
            @Query("year") String year
    );
    @GET("api_files_fp_byIssueID.php")
    Call<List<ResponseCurrent>> requestChildFrontPages(
            @Query("issue_id") String id
    );

    @GET("api_files_bp_byIssueID.php")
    Call<List<ResponseCurrent>> requestChildBackPages(
            @Query("issue_id") String id
    );

    @GET("api_files_fe_byIssueID.php")
    Call<List<ResponseCurrent>> requestChildFullPages(
            @Query("issue_id") String id
    );

    @GET("api_files_fe_byIssueID.php")
    Call<List<ResponseCurrent>> requestFullEditionPages(
            @Query("issue_id") String id
    );

    @GET("api_files_art_byIssueID.php")
    Call<List<ResponseCurrent>> requestChildArticlePages(
            @Query("issue_id") String id
    );

    @GET("api_author_byID.php")
    Call<List<ResponseAuthor>> requestAuthor(
            @Query("id") String id
    );

    @GET("api_notification.php")
    Call<List<ResponseNotification>> requestNotifiication();

    @GET("api_notification_byID.php")
    Call<List<ResponseNotification>> requestNotifiicationByJournalID(
            @Query("id") String journalID
    );



}