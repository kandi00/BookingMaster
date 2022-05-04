using ApiHeroku.Data.Model;
using ApiHeroku.Data.Response;
using ApiHeroku.Data.ViewModel;
using ApiHeroku.Exceptions;
using ApiHeroku.Services;
using ApiHeroku.Utils;
using Microsoft.AspNetCore.Mvc;

namespace ApiHeroku.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class BookingController : ControllerBase
    {

        private readonly IBookingService _bookingService;

        public BookingController(IBookingService bookingService)
        {
            _bookingService = bookingService;
        }

        [HttpPost,Route("add-new-booking")]
        public async Task<ActionResult<Booking>> AddNewBooking([FromBody] BookingViewModel newBooking)
        {

            try
            {
                BookingResponse result = await _bookingService.AddNewBooking(newBooking);
                return Ok(result);
            }
            catch (PostRequestException ex)
            {
                BookingResponse errorResponse = new BookingResponse()
                {
                    Code = 500,
                    Message = APIErrorCodes.POST_REQUEST_EXCEPTION_MESSAGE + ex.Message
                };
                return BadRequest(errorResponse);
            }
        }
        [HttpGet, Route("get-bookings-by-user")]
        public async Task<ActionResult<IEnumerable<Booking>>> GetBookingsByUser([FromHeader] string? email)
        {

            try
            {
                BookingsByUserResponse result = await _bookingService.GetBookingsByUser(email);
                return Ok(result);
            }
            catch (PostRequestException ex)
            {
                BookingsByUserResponse errorResponse = new BookingsByUserResponse()
                {
                    Code = 500,
                    Message = APIErrorCodes.POST_REQUEST_EXCEPTION_MESSAGE + ex.Message
                };
                return BadRequest(errorResponse);
            }
        }
        
        [HttpDelete("delete-booking")]
        public async Task<IActionResult> DeleteBooking([FromHeader] int ID)
        {
            try
            {
                DeleteBookingResponse result = await _bookingService.DeleteBooking(ID);
                return Ok(result);
            }
            catch (PostRequestException ex)
            {
                DeleteBookingResponse errorResponse = new DeleteBookingResponse()
                {
                    Code = 500,
                    Message = APIErrorCodes.POST_REQUEST_EXCEPTION_MESSAGE + ex.Message
                };
                return BadRequest(errorResponse);
            }
        }


    }

}
