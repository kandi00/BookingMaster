using BookingMaster.Data.Model;
using BookingMaster.Data.Response;
using BookingMaster.Exceptions;
using BookingMaster.Services;
using BookingMaster.Utils;
using Microsoft.AspNetCore.Mvc;

namespace BookingMaster.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class RoomController : ControllerBase
    {
        private readonly IRoomService _roomService;

        public RoomController(IRoomService roomService)
        {
            _roomService = roomService;
        }

        [HttpGet, Route("location")]
        public async Task<ActionResult<IEnumerable<Room>>> GetRoomsByLocation([FromHeader] string? Location)
        {
            try
            {
                RoomResponse result = await _roomService.GetRoomsByLocation(Location);
                return Ok(result);

            }
            catch (GetException ex)
            {
                AccommodationsResponse errorResponse = new AccommodationsResponse()
                {
                    Code = 400,
                    Message = APIErrorCodes.GET_REQUEST_EXCEPTION_MESSAGE + ex.Message
                };
                return BadRequest(errorResponse);
            }
        }
    }
}
