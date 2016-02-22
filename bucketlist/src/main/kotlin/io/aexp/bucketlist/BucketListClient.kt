package io.aexp.bucketlist

import io.aexp.bucketlist.data.Order
import io.aexp.bucketlist.data.PagedResponse
import io.aexp.bucketlist.data.PullRequest
import io.aexp.bucketlist.data.PullRequestActivity
import io.aexp.bucketlist.data.PullRequestCommit
import io.aexp.bucketlist.data.PullRequestState
import rx.Observable

interface BucketListClient {

    /**
     * @return observable of PRs, newest first (unless order is specified)
     */
    fun getPrs(projectKey: String, repoSlug: String, prState: PullRequestState, order: Order = Order.NEWEST):
            Observable<PagedResponse<PullRequest>>

    /**
     * @return observable that emits a single PullRequest
     */
    fun getPr(projectKey: String, repoSlug: String, prId: Int): Observable<PullRequest>

    /**
     * @return observable of PR activity, newest first
     */
    fun getPrActivity(projectKey: String, repoSlug: String, prId: Long):
            Observable<PagedResponse<PullRequestActivity>>

    /**
     * @return observable of PR commits
     */
    fun getPrCommits(projectKey: String, repoSlug: String, prId: Long):
            Observable<PagedResponse<PullRequestCommit>>

    /**
     * @param fromId the commit or treeish that is the source of the PR, e.g. "refs/heads/some-new-branch"
     * @param toId the commit or treeish that is the destination of the PR, e.g. "refs/heads/master"
     */
    fun createPr(projectKey: String, repoSlug: String, title: String, description: String, fromId: String,
                 toId: String): Observable<PullRequest>
}
