using BookingMaster.Data.Response;
using BookingMaster.Exceptions;
using BookingMaster.Repositories;
using BookingMaster.Utils;

namespace BookingMaster.Services
{
    public class RoomService : IRoomService
    {
        private readonly IRoomRepository _roomRepository;

        public RoomService(IRoomRepository roomRepository)
        {
            _roomRepository = roomRepository;
        }
        public async Task<RoomResponse> GetRoomsByLocation(string? Location)
        {
            try
            {
                RoomResponse response = new RoomResponse();
                response.Rooms = await _roomRepository.GetRoomsByLocation(Location); ;

                if (response.Rooms != null)
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
    }
}
