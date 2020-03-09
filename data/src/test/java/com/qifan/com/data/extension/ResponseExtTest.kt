package com.qifan.com.data.extension

import com.qifan.data.entity.DefaultResponse
import com.qifan.data.entity.ErrorType
import com.qifan.data.extension.processApiResponse
import com.qifan.domain.model.base.Results
import com.qifan.domain.model.exception.TheForkException
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ResponseExtTest {
    @Mock
    private lateinit var mockDefaultResponse: DefaultResponse

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }


    @Test
    fun test_when_response_contain_error_type_return_failure() {
        given(mockDefaultResponse.errorType).willReturn(ErrorType.UNKNOWN)
        Assert.assertTrue(
            processApiResponse(mockDefaultResponse) is Results.Failure
        )
    }

    @Test
    fun test_when_response_contain_restaurant_not_found_return_failure() {
        given(mockDefaultResponse.errorType).willReturn(ErrorType.RESTAURANT_NOT_FOUND)
        Assert.assertTrue(
            (processApiResponse(mockDefaultResponse) as Results.Failure).error is TheForkException.EmptyException
        )
    }


    @Test
    fun test_when_response_contain_other_type_return_failure() {
        given(mockDefaultResponse.errorType).willReturn(ErrorType.JSON_PARSING_EXCEPTION)
        Assert.assertTrue(
            (processApiResponse(mockDefaultResponse) as Results.Failure).error is TheForkException.NetworkException
        )
    }
}

