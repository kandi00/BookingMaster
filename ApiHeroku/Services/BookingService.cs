using ApiHeroku.Data.Model;
using ApiHeroku.Data.Response;
using ApiHeroku.Data.ViewModel;
using ApiHeroku.Exceptions;
using ApiHeroku.Repositories;
using ApiHeroku.Utils;

namespace ApiHeroku.Services
{
    public class BookingService : IBookingService
    {
        private readonly IBookingRepository _bookingRepository;

        public BookingService(IBookingRepository bookingRepository)
        {
            _bookingRepository = bookingRepository;
        }
        public async Task<BookingResponse> AddNewBooking(BookingViewModel newBooking)
        {
            Booking booking = new Booking()
            {
                from_date = newBooking.from_date,
                to_date = newBooking.to_date,
                booking_date = DateOnly.FromDateTime(DateTime.Now),
                RoomId = newBooking.RoomId,
                UserId = newBooking.UserId
            };
            BookingResponse response = new BookingResponse();

            try
            {
                response.Booking = await _bookingRepository.AddNewBooking(booking);
                if (response.Booking != null)
                {
                    response.Code = 200;
                    response.Message = APISuccessCodes.POST_BOOKING_SUCCESS;
                }
                return response;
            }
            catch (PostRequestException ex)
            {
                throw new PostException(ex.Message);
            }
        }

        

        public async Task<BookingsByUserResponse> GetBookingsByUser(string? Email)
        {
            try
            {
                BookingsByUserResponse response = new BookingsByUserResponse();
                response.BookingsByUser = await _bookingRepository.GetBookingsByUser(Email); ;

                if (response.BookingsByUser != null)
                {
                    response.Code = 200;
                    response.Message = APISuccessCodes.GET_ACCOMMODATION_SUCCESS;
                }
                return response;
            }
            catch (GetRequestException ex)
            {
                throw new GetException(ex.Message);
            }
        }
        /*// DELETE: api/ToDoes/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteToDo(int id)
        {
            var toDo = await _context.ToDos.FindAsync(id);
            if (toDo == null)
            {
                return NotFound();
            }

            _context.ToDos.Remove(toDo);
            await _context.SaveChangesAsync();

            return NoContent();
        }

        private bool ToDoExists(int id)
        {
            return _context.ToDos.Any(e => e.Id == id);
        }*/
        public async Task<DeleteBookingResponse> DeleteBooking(int ID)
        {
            try
            {
                DeleteBookingResponse response = new DeleteBookingResponse();
                response.noError = await _bookingRepository.DeleteBooking(ID); ;

                if (response.noError == true)
                {
                    response.Code = 200;
                    response.Message = APISuccessCodes.GET_ACCOMMODATION_SUCCESS;
                }
                else
                {
                    response.Code = 500;
                    response.Message = "no booking with such id";
                }
                return response;
            }
            catch (GetRequestException ex)
            {
                throw new GetException(ex.Message);
            }
        }


    }


}
