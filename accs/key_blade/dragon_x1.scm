	� G $/accs/dragon_master_black/dragon.skn n�       �V�n�F]S����Q�rhjF�����R��Va�EwĈ�S��=�B@���Ȧ@wA7)�n��~K�8�g�Ό(�z.,�9sy��M'�:��#%'�G�F�ι�P�6r,y$�><bN����Z6w'Yk�ED��!���X4�[5�0�ņ����������~|�˟��~~s���X~��~~���^]o�6r�ց���%�^b��Ur���Ft����us3c�
�m䌀w(8N�������m�~z�w���z�H��"l-�ITrH�奫����"o�`�kfS_v���.��<�Ϛ�M:��TDc�@6���9bt<;(�/�N����YF��|�/����@]�ėE���B'^�f�<��éjf��Z���z��1]sl�E�ŏ�q{���/��j�{,�<P�g�	����3`�FW!�L��=>j>�8:mjڿ+�bI������:<L\{�瑅m5��%�<�
#����*P�1B);��Z魡O����*i���I��$V6&��.��� ��^Apy�H=�A<o�,`�4��h��[j!���-���"*u4�v��h��dH�'�N�%3�%�)�C�qj�e�:'���
 ���̸̫m�O�c]�}�Q�����P���ւ�b�a2���\�k�Tf��1�H�~������a"$�,����*QP���+ym��.�c�(d%U����$�@���j��Zǭ3���`��(���RB��;��FNIF�)F%�:�M�X*�	��7dqY���zF�x�8�*m�h�0iNB���q���u˝��P�)��\U�\�on��{����o�x������?�f��H��c%�ڲ�3J�lWmU�ҫκFFz�;H��~󂽸R�1Z �=H�;K��J�.e �kSٛ��(�7W��7C�O2��P\�#Q��Y�Ve��痜�} LU4�m"{W�M#dl��^f�>�e�u'x�f�Ѥ2�˓ջ�0�rb/�<��.�'V@�� Z�@������<��_g�:�aD��;k�Ɉ�?J�>���z?�e�b��
`E/;m�n���5�?�p��jAx�m��,�,?H�׷�ŏ���&��hUΦ������
   w  Lga2/stage/AmpedScript; e Lkotori/data/MixtureData; mix�� kotori.data.MixtureData� /efx/mix/bar2.agi���� Lkotori/data/KGLGenerationData; windD /efx/tornado/wind.scm�            ?�  ?�  ?�               glow_white2D /efx/flash/glow01_white2.ptc�        ?   @�  @�  @�                
kira_17_wbD /efx/kirakira/17_wb.ptc�        ?   @   @   @                 start timeout setRenderType
 d s 3  / setBlend
 d v 3 setColor (FFFF)V
 d x y setShadowState
 d { 3 roll (Lkotori/geom/Vector3D;I)V
 f } ~-        D�   anime (F)V
 d � �B�   	playSound
 d � 3 
setMixture (Lkotori/data/MixtureData;)V
 d � � Lkotori/scp/KotoriScript; � 
accumlimit
 � � 3 size
 f � �<��� j pos (FFF)V
 � � � rndc (F)F
 H � � hpb
 � � �A�  B�   scale
 � � �@    parts	 � �  connect	 � �  generate A(Ljava/lang/Object;Ljava/lang/String;)Lkotori/gfx/KGLScriptModel;
 d � � 	start_bluC4   /(Ljava/lang/Object;)Lkotori/gfx/KGLScriptModel;
 d � �
 H 0 3-             rotate (Lkotori/geom/Quaternion;)V
 f � ��� kotori.geom.Quaternion�;�.    ?�       (FF)V
 d � �C  >�� mixtureMotion 	(IFFFFF)V
 d � �C�  D /efx/flash/glow01_white2.ptc =��;L��    @   @   @               =����L��D /efx/fire/fire_ball.scm=L�ͼ��
    >���>���>���             move
 f � �<�/ (FFFZ)V
 d � �C  @�  -�          C  A   -            ���"�4���.?]�׿   C   vibe (FFFFI)V
 d � �>L��G�P  yquake
 d � 3 attack E(Lkotori/geom/Vector3D;Lga2/stage/atk/AttackParam;)Lga2/stage/Attack;
 f � �-        ?�  � d��D $/efx/fire/fire_ball_key_blade_x1.scm�            ?�  ?�  ?�  C4          � hit_fe( d� !  �  �hp'       ,�>�        C   	fadeColor
 d � �D /efx/flash/glow01_white2.ptc     >L��    @   @   @                 kill
 f � 1 f h g k j m j o j - 1  i� l�n�p��  q 1  �*u� tW*� wW*� zW*� |W*�� W*�� �W*� �W*� � �W<� �* ȶ �W*��j� �W�� �W��� ���� �� �W��� �W�� ��� �*��� �W
p� &��� ��� ��� �� �W*�� �W*�� �W*
� �W�W(��|�  r 1 w*� �W*u� tW*� wW*� zW*�� W*�� �W*��� �W*�� �W*�� �W<� D���� �W��� ��� ��� �� �W�� �W�� ��� �*��� �W�W
���*�� �W<� *�� �W*
� �W�W���*���� �W* ȶ �W*�� W*���� �W*d� �W*�� W*Ƕ �W*���� �W*��,� �W*� �W* �� �W*�Զ �W*2� �W*��� �W* ȶ �W*� tW*� wW*,� �W*� |W*ض �W*,� �W*� �W� efz�    ���j<���?|�C>a�w?/���  